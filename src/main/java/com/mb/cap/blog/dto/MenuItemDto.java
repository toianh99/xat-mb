package com.mb.cap.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.mb.cap.blog.entity.MenuItem}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MenuItemDto implements Serializable {
    private boolean active;
    private Long id;
    private String title;
    private String code;
    private String path;
    private String icon;
    private Long parentId;
    private Integer orderId;
    private String description;
    private Boolean isDirectory;
}