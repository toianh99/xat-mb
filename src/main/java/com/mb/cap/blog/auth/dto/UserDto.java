package com.mb.cap.blog.auth.dto;

import com.mb.cap.blog.auth.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String id;
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

    public static User toEntity(UserDto dto) {
        User entity = new User();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setFullName(dto.getFullName());
        entity.setBirthDate(dto.getBirthDate());
        entity.setAvatar(dto.getAvatar());
        entity.setTotalLoginFailures(dto.getTotalLoginFailures());
        entity.setActive(dto.getActive());
        entity.setChangePassword(dto.getChangePassword());
        entity.setChangePasswordDate(dto.getChangePasswordDate());
        return entity;
    }
}
