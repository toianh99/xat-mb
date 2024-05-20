package com.mb.cap.blog.controller;

import com.mb.cap.blog.dto.CategoryDto;
import com.mb.cap.blog.service.ICategory;
import com.mb.cap.blog.utils.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/category")
@RestController
@Slf4j
@RequiredArgsConstructor
public class CategoryController {
    @Autowired
    private ICategory categoryService;

    @PostMapping("/save")
    public CommonResponse<?> saveCategory(@RequestBody CategoryDto dto) {
        try {
            categoryService.saveCategory(dto);
            return CommonResponse.success();
        } catch (Exception e) {
            return CommonResponse.fail("500", e.getMessage(), null);
        }
    }

    @PutMapping("/{id}/update")
    public CommonResponse<?> updateCategory(@PathVariable Long id, @RequestBody CategoryDto dto) {
        try {
            categoryService.updateCategory(dto, id);
            return CommonResponse.success();
        } catch (Exception e) {
            return CommonResponse.fail("500", e.getMessage(), null);
        }
    }

    @GetMapping("/{id}")
    public CommonResponse<?> getCategory(@PathVariable Long id) {
        try {
            return CommonResponse.success(categoryService.getCategory(id));
        } catch (Exception e) {
            return CommonResponse.fail("500", e.getMessage(), null);
        }
    }

    @DeleteMapping("/{id}/delete")
    public CommonResponse<?> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return CommonResponse.success();
        } catch (Exception e) {
            return CommonResponse.fail("500", e.getMessage(), null);
        }
    }

    @GetMapping("/list")
    public CommonResponse<?> listCategory(Pageable pageable) {
        try {
            return CommonResponse.success(categoryService.getAllCategory(pageable));
        } catch (Exception e) {
            return CommonResponse.fail("500", e.getMessage(), null);
        }
    }
}
