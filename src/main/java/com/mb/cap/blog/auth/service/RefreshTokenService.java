package com.mb.cap.blog.auth.service;

import com.mb.cap.blog.auth.entity.RefreshToken;
import com.mb.cap.blog.auth.exception.TokenRefreshException;
import com.mb.cap.blog.auth.repo.RefreshTokenRepository;
import com.mb.cap.blog.auth.entity.User;
import com.mb.cap.blog.auth.repo.UserRepository;
import com.mb.cap.blog.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Value("${app.auth.refreshTokenDurationMs}")
    private Long refreshTokenDurationMs;
    @Autowired
    private UserRepository userRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return this.refreshTokenRepository.findByToken(token);
    }

    public Optional<RefreshToken> findByUserId(String token) {
        return this.refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(String userId) {
        User user = (User)this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user not found"));
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUserId(user.getId());
        refreshToken.setExpiryDate(Instant.now().plusMillis(this.refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken = this.refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            this.refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        } else {
            return token;
        }
    }

    @Transactional
    public int deleteByUserId(String userId) {
        User user = (User)this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user not found"));
        return this.refreshTokenRepository.deleteByUser(userId);
    }
}
