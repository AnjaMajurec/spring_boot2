package algebra2.example.spring_boot2.user;

public interface UserService {
    User findById(Long id);
    User findByUsername(String username);
}
