package algebra2.example.spring_boot2.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username); // da se ne mora vratiti User u svakom slučaju-zato je Optional
}
