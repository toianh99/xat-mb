package com.mb.cap.blog.repo;

import com.mb.cap.blog.entity.MenuItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    @Query("select m from MenuItem m where m.active = :active")
    Page<MenuItem> findAllByActive(boolean active, Pageable pageable);
}