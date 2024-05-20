package com.mb.cap.blog.auth.entity;

import com.mb.cap.blog.entity.BaseEntity;
import com.mb.cap.blog.utils.IdUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "xat_user_role")
public class UserRole extends BaseEntity {
    @Id
    private Long id = IdUtil.nextId();
    private Long userId;
    private Long roleId;
}
