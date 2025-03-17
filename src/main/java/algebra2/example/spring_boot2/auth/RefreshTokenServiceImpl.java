package algebra2.example.spring_boot2.auth;

import algebra2.example.spring_boot2.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;

    @Override
    public RefreshToken findByUserId(Long userId){
        Optional<RefreshToken> refreshToken=refreshTokenRepository.findByUserId(userId);

        if(refreshToken.isEmpty()){
            throw new RuntimeException("Refresh token not found");
        }
        return  refreshToken.get();
    }
    @Override
    public RefreshToken generateRefreshToken(Long userId){
        String token= jwtService.generateToken(userId.toString());
        Date expiresAt=jwtService.extractExpirationDate(token);
        RefreshToken refreshToken=new RefreshToken(token, userId, expiresAt);
        return refreshTokenRepository.save(refreshToken);

    }
}
