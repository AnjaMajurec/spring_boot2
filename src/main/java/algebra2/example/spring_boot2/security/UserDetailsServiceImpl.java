package algebra2.example.spring_boot2.security;

import algebra2.example.spring_boot2.user.User;
import algebra2.example.spring_boot2.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService { //implements method(bit ce crveno
    @Autowired //drugi način dependency injectiona, automatski kad se radi u UserDetailsServiceImpl, injektiraj UserRepository
    private UserRepository userRepository;

    @Override //povratni tip je UserDetails-zato je potrebno napraviti CostumUserDetails-kako bi izvukli username,password
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user=userRepository.findByUsername(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("Wrong credentials");
        }
        //return new CustomUserDetails(user.get().getUsername(), user.get().getPassword());
        return new CustomUserDetails(user.get());
    }
}
