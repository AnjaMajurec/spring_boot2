package algebra2.example.spring_boot2.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtService {
    private final String SECRET_KEY="YR6NKUdqjvelpRGGQs91jFBEPnhCVo0Irsx1uHCn4eNYbaMPpuMEs4M832bYVKc7"; //64 charactera mora biti key //rad sa JWT tokenom, moramo imat secret da ga možemo dekodirat

    public boolean validateToken(String token, UserDetails userDetails){//userDetails spremi se tijekom log-in-a //provjera da je token ispravan ili da li je istekao
        String username = extractUsername(token);
        boolean isUsernameValid = username.equals(userDetails.getUsername());

        if (!isUsernameValid){
            return false;
        }

        boolean isTokenExpired = checkIsTokenExpired(token);

        if (isTokenExpired) {
            return false;
        }

        return true;
    }
    private boolean checkIsTokenExpired(String token){
        Date expirationDate = extractExpirationDate(token);
        boolean isExpired = expirationDate.before(new Date());
        return isExpired;
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject); //prvi ulazni, drugi izlazni
    }
    public Date extractExpirationDate(String token){
        Claims allClaims=extractAllClaims(token);
        return allClaims.getExpiration();
    }
//više povratnih tipova T
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){ //generični tipovi return
        Claims claims=extractAllClaims(token); //moze return string, date... resolver-koji dio koda zelimo nadodati na njega
        return claimsResolver.apply(claims); //pretvorimo json body, stavimo resolver, preko getSubjecta dohvati username
    }

    private Claims extractAllClaims(String token){ //dohvatili sve iz jwt tokena
        return Jwts.parser() //parsira nekakav token
                .setSigningKey(SECRET_KEY) //definirali gore
                .build()
                .parseClaimsJws((token))
                .getBody();
    }
    public String generateAccessToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date()) //trenutno vrijeme
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60)) //traje 1 sat
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String generateRefreshToken(String userId){
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date()) //trenutno vrijeme
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*100)) //traje 100h
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

}
