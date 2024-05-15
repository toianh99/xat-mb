package com.mb.cap.blog.service.impl;

import com.mb.cap.blog.constants.StatusEnum;
import com.mb.cap.blog.dto.MenuItemDto;
import com.mb.cap.blog.entity.MenuItem;
import com.mb.cap.blog.repo.MenuItemRepository;
import com.mb.cap.blog.service.IMenuItemService;
import com.mb.cap.blog.utils.MapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MenuItemServiceImpl implements IMenuItemService {
    @Autowired
    private MenuItemRepository menuItemRepository;

    @Override
    public void saveMenuItem(MenuItemDto dto) {
        MenuItem menuItem = new MenuItem();
        MapperUtils.copy(dto, menuItem);
        menuItemRepository.save(menuItem);
    }

    @Override
    @Transactional
    public void updateMenuItem(Long id, MenuItemDto dto) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        MapperUtils.copy(dto, menuItem);
        menuItemRepository.save(menuItem);
    }

    @Override
    @Transactional
    public void deleteMenuItem(Long id) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        menuItem.setActive(false);
        menuItemRepository.save(menuItem);
    }

    @Override
    public MenuItemDto getMenuItem(Long id) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        MenuItemDto dto = new MenuItemDto();

        MapperUtils.copy(menuItem, dto);
        return dto;
    }

    @Override
    public Page<MenuItemDto> getAllMenuItem(Pageable pageable) {
        Page<MenuItem> menuItemPage = menuItemRepository.findAllByActive(StatusEnum.INACTIVE.value, pageable);
        if (!CollectionUtils.isEmpty(menuItemPage.getContent())) {
            List<MenuItemDto> menuItemDtos = MapperUtils.mapList(menuItemPage.getContent(), MenuItemDto.class);
            return new PageImpl<>(menuItemDtos, pageable, menuItemPage.getTotalElements());
        }

        return new PageImpl<>(List.of(), pageable, 0);
    }
}
