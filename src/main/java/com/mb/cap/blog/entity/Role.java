package com.mb.cap.blog.entity;

import com.mb.cap.blog.utils.IdUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "role", indexes = {
        @Index(name = "role_idx", columnList = "name, code")
})
@Getter
@Setter
public class Role extends BaseEntity implements GrantedAuthority {
    @Id
    private Long id = IdUtil.nextId();
    private String name;
    private String code;
    private String description;

    @Override
    public String getAuthority() {
        return this.name;
    }
}
