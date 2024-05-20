package com.mb.cap.blog.auth.repo;

import com.mb.cap.blog.auth.entity.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, Long> {

    @Query("SELECT a FROM AccessToken a WHERE a.accessToken = :token")
    AccessToken findByToken(String token);

    @Query("SELECT a FROM AccessToken a WHERE a.userId = :userId")
    List<AccessToken> findByUserId(String userId);
}