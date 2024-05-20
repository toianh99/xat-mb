package com.mb.cap.blog.auth.service;

import com.mb.cap.blog.auth.entity.AccessToken;
import com.mb.cap.blog.auth.entity.User;
import com.mb.cap.blog.auth.repo.AccessTokenRepository;
import com.mb.cap.blog.auth.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class AccessTokenService {
    @Autowired
    AccessTokenRepository accessTokenRepository;
    @Autowired
    private UserRepository userRepository;

    public AccessToken createToken(String token, String userId) {
        AccessToken accessToken = this.accessTokenRepository.findByToken(token);
        if (accessToken == null) {
            accessToken = new AccessToken();
            accessToken.setAccessToken(token);
            User user = (User) this.userRepository.findById(userId).get();
            accessToken.setUserId(user.getId());
            accessToken.setCreatedDate(Instant.now());
        }

        return this.accessTokenRepository.save(accessToken);
    }

    public AccessToken findByToken(String token) {
        return this.accessTokenRepository.findByToken(token);
    }

    public void deleteByUserId(String userId) {
        List<AccessToken> list = this.accessTokenRepository.findByUserId(userId);
        this.accessTokenRepository.deleteAll(list);
    }
}