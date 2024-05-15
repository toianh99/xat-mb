package com.mb.cap.blog.service;

import com.mb.cap.blog.dto.MenuItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMenuItemService {
    void saveMenuItem(MenuItemDto dto);
    void updateMenuItem(Long id, MenuItemDto dto);
    void deleteMenuItem(Long id);
    MenuItemDto getMenuItem(Long id);
    Page<MenuItemDto> getAllMenuItem(Pageable pageable);
}
