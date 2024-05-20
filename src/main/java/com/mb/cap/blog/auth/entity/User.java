package com.mb.cap.blog.auth.entity;

import com.mb.cap.blog.entity.BaseEntity;
import com.mb.cap.blog.utils.IdUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "xat_user", indexes = {
        @Index(name = "user_idx", columnList = "username, email, phone")
})
@Getter
@Setter
public class User extends BaseEntity implements UserDetails {
    @Id
    private String id = IdUtil.generateUUID();
    private String username;
    private String password;
    private String email;
    private String phone;
    private String fullName;
    private String birthDate;
    private String avatar;
    private Long totalLoginFailures;
    private Boolean active = false;
    private Boolean changePassword = false;
    private String changePasswordDate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet();
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
