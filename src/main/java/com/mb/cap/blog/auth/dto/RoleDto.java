package com.mb.cap.blog.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * DTO for {@link com.mb.cap.blog.auth.entity.Role}
 */
@AllArgsConstructor
@Getter
public class RoleDto implements Serializable {
    private Long id;
    private String name;
    private String code;
    private String description;
}