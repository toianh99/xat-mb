package com.mb.cap.blog.service;

import com.mb.cap.blog.dto.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategory {
    void saveCategory(CategoryDto dto);
    void updateCategory(CategoryDto dto, Long id);
    void deleteCategory(Long id);
    CategoryDto getCategory(Long id);
    Page<CategoryDto> getAllCategory(Pageable pageable);
}
