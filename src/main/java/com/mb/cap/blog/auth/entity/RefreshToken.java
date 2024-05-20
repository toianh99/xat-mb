package com.mb.cap.blog.auth.entity;

import com.mb.cap.blog.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "refresh_token")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken extends BaseEntity {
    @Id
    private Long id;
    private String token;
    private String userId;
    private Instant expiryDate;
}
