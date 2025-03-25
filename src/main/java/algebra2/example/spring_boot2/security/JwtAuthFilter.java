package algebra2.example.spring_boot2.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter { //alt+insert-override-do filterInternal(request,response..)
    //prije nego request uđe u našu app, prvo ide u filter
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String url=request.getRequestURI();
        if(url.equals("/auth/api/v1/login") || url.equals("/auth/api/v1/refreshToken")){
            filterChain.doFilter(request,response);
            return;
        }
        String authHeader=request.getHeader("Authorization"); //naziv bilo koji, no mora biti syncan s nazivom headera u postmanu
        String token=null;
        String username=null;


        if(authHeader==null){
            throw new ServletException();
        }
        if(!authHeader.startsWith("Bearer ")){ //bearer-ime tokena
            throw new ServletException();
        }
        token=authHeader.substring(7); //metoda za obrisat prvih 7 znakova
        username= jwtService.extractUsername(token);

        UserDetails userDetails= userDetailsService.loadUserByUsername(username);
        boolean isTokenValid= jwtService.validateToken(token,userDetails);
        if(isTokenValid){
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,new ArrayList<>()); //defaultna provjera
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); //WebAuthenticationDetailsSource za rad sa spring boot-om
            SecurityContextHolder.getContext().setAuthentication(authenticationToken); // u context stavimo kreirani autentificirani token, odnosno imamo token unutar requesta

        }
        filterChain.doFilter(request,response); //idi mi na iduci filter i proslijedi trenutni request i response
    }
}
