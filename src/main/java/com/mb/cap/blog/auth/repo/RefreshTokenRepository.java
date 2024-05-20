package com.mb.cap.blog.auth.repo;

import com.mb.cap.blog.auth.entity.RefreshToken;
import com.mb.cap.blog.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    @Query("SELECT t FROM RefreshToken t WHERE t.token = :token")
    Optional<RefreshToken> findByToken(String token);

    @Modifying
    @Query("delete FROM RefreshToken t WHERE t.userId = :userId")
    int deleteByUser(String userId);
}