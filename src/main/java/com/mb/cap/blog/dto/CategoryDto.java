package com.mb.cap.blog.dto;

import com.mb.cap.blog.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * DTO for {@link Category}
 */
@AllArgsConstructor
@Getter
public class CategoryDto implements Serializable {
    private final boolean active = true;
    private final Long id;
    private final String name;
    private final String code;
    private final String description;
}