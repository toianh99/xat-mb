package com.mb.cap.blog.auth.entity;

import com.mb.cap.blog.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "access_token", indexes = {
        @Index( name = "access_token_idx", columnList = "id, createdDate, updatedDate")
})
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccessToken extends BaseEntity {
    @Id
    private Long id;
    private String accessToken;
    private String userId;
}
