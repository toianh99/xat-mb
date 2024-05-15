package com.mb.cap.blog.service.impl;

import com.mb.cap.blog.constants.StatusEnum;
import com.mb.cap.blog.entity.Category;
import com.mb.cap.blog.dto.CategoryDto;
import com.mb.cap.blog.exception.NotFoundException;
import com.mb.cap.blog.repo.CategoryRepo;
import com.mb.cap.blog.service.ICategory;
import com.mb.cap.blog.utils.MapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryImpl implements ICategory {
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    @Transactional
    public void saveCategory(CategoryDto dto) {
        Category category = new Category();
        MapperUtils.copy(dto, category);

        categoryRepo.save(category);
    }

    @Override
    public void updateCategory(CategoryDto dto, Long id) {
        Category category = categoryRepo.findByActiveAndId(StatusEnum.ACTIVE.value, id)
                .orElseThrow(() -> new NotFoundException("Category not found"));

        MapperUtils.mapIgnoreNull(dto, category);
        categoryRepo.save(category);
    }

    @Override
    @Modifying
    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryRepo.findByActiveAndId(StatusEnum.ACTIVE.value, id)
                .orElseThrow(() -> new NotFoundException("Category not found"));

        category.setActive(StatusEnum.INACTIVE.value);
        categoryRepo.save(category);
    }

    @Override
    public CategoryDto getCategory(Long id) {
        Category category = categoryRepo.findByActiveAndId(StatusEnum.ACTIVE.value, id)
                .orElseThrow(() -> new NotFoundException("Category not found"));

        return MapperUtils.map(category, CategoryDto.class);
    }

    @Override
    public Page<CategoryDto> getAllCategory(Pageable pageable) {
        Page<Category> categoryPage = categoryRepo.findAllByActive(StatusEnum.ACTIVE.value, pageable);
        if (!CollectionUtils.isEmpty(categoryPage.getContent())) {
            List<Category> categories = categoryPage.getContent();
            List<CategoryDto> categoryDtos = MapperUtils.mapList(categories, CategoryDto.class);

            return new PageImpl<>(categoryDtos, categoryPage.getPageable(), categoryPage.getTotalElements());
        }

        return new PageImpl<>(List.of());
    }
}
