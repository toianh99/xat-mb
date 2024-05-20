package com.mb.cap.blog.auth.controller;

import com.mb.cap.blog.auth.entity.RefreshToken;
import com.mb.cap.blog.auth.entity.User;
import com.mb.cap.blog.auth.request.LoginRequest;
import com.mb.cap.blog.auth.request.TokenRefreshRequest;
import com.mb.cap.blog.auth.response.JwtResponse;
import com.mb.cap.blog.auth.service.AccessTokenService;
import com.mb.cap.blog.auth.service.RefreshTokenService;
import com.mb.cap.blog.auth.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/auth")
@RestController
public class AuthController {
    @Autowired
    private AccessTokenService accessTokenService;
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private AuthenticationManagerBuilder authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping(
            path = {"/login"},
            consumes = {"application/json"}
    )
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = this.authenticationManager.getObject().authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User userDetails = (User)authentication.getPrincipal();
        String jwt = this.jwtUtils.generateJwtToken(userDetails);
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        RefreshToken refreshToken = this.refreshTokenService.createRefreshToken(userDetails.getId());
        this.accessTokenService.createToken(jwt, userDetails.getId());
        return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
    }

    @PostMapping(
            path = {"/token"},
            consumes = {"application/x-www-form-urlencoded"}
    )
    public ResponseEntity<?> authenticate(LoginRequest loginRequest) {
        Authentication authentication = this.authenticationManager.getObject().authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User userDetails = (User)authentication.getPrincipal();
        String jwt = this.jwtUtils.generateJwtToken(userDetails);
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        RefreshToken refreshToken = this.refreshTokenService.createRefreshToken(userDetails.getId());
        this.accessTokenService.createToken(jwt, userDetails.getId());
        return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
    }

    @PostMapping({"/refresh-token"})
    public ResponseEntity<?> refreshToken(TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        Optional<RefreshToken> refreshToken = this.refreshTokenService.findByToken(requestRefreshToken);
        RefreshTokenService var10001 = this.refreshTokenService;
        Objects.requireNonNull(var10001);
        RefreshToken tokenRe = refreshToken.map(var10001::verifyExpiration).orElse(null);
        if (tokenRe != null) {
            String username = tokenRe.getUserId();
            return this.refreshTokenService.deleteByUserId(username) > 0 ? ResponseEntity.ok("Refresh token deleted successfully!") : ResponseEntity.ok("Refresh token not deleted successfully!");
        } else {
            return ResponseEntity.ok("Refresh token not found!");
        }
    }

    @PostMapping({"/logout"})
    public ResponseEntity<?> logoutUser() {
        try {
            User userDetails = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String userId = userDetails.getId();
            this.refreshTokenService.deleteByUserId(userId);
            this.accessTokenService.deleteByUserId(userId);
            return ResponseEntity.ok("Logout successful!");
        } catch (Exception var3) {
            return ResponseEntity.ok("Logout not successful!");
        }
    }
}
