package algebra2.example.spring_boot2.auth;

public interface RefreshTokenService {
    RefreshToken findByUserId(Long userId);
    RefreshToken generateRefreshToken(Long userId);
}
