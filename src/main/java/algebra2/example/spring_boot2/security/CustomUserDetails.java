package algebra2.example.spring_boot2.security;

import algebra2.example.spring_boot2.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {//ako javi grešku, override metode
    private String username;
    private String password;

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return new ArrayList<>(); //znaci da korisnik nema nikakve permisije
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public CustomUserDetails(User user){
        this.username=user.getUsername();
        this.password=user.getPassword();

    }
    //može i ovako
    public CustomUserDetails(String username, String password){
        this.username=username;
        this.password=password;
    }
}
