package com.mb.cap.blog.auth.utils;

import com.mb.cap.blog.auth.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    @Value("${app.jwtSecret}")
    private String jwtSecret;
    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public JwtUtils() {
    }

    public String generateTokenFromUsername(String username) {
        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + (long)this.jwtExpirationMs);
        return Jwts.builder().setSubject(username).setIssuedAt(issuedDate).setExpiration(expiredDate).signWith(this.key(), SignatureAlgorithm.HS256).compact();
    }

    public String generateJwtToken(User userPrincipal) {
        return this.generateTokenFromUsername(userPrincipal.getUsername());
    }

    private Key key() {
        return Keys.hmacShaKeyFor((byte[]) Decoders.BASE64.decode(this.jwtSecret));
    }

    public String getIdFromJwtToken(String token) {
        return ((Claims)Jwts.parserBuilder().setSigningKey(this.key()).build().parseClaimsJws(token).getBody()).getId();
    }

    public String getUserNameFromJwtToken(String token) {
        return ((Claims)Jwts.parserBuilder().setSigningKey(this.key()).build().parseClaimsJws(token).getBody()).getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(this.key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException var3) {
            logger.error("Invalid JWT token: {}", var3.getMessage());
        } catch (ExpiredJwtException var4) {
            logger.error("JWT token is expired: {}", var4.getMessage());
        } catch (UnsupportedJwtException var5) {
            logger.error("JWT token is unsupported: {}", var5.getMessage());
        } catch (IllegalArgumentException var6) {
            logger.error("JWT claims string is empty: {}", var6.getMessage());
        }

        return false;
    }
}
