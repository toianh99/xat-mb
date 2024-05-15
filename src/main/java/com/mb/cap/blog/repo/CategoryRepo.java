package com.mb.cap.blog.repo;

import com.mb.cap.blog.constants.StatusEnum;
import com.mb.cap.blog.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    @Query("select c from Category c where c.active = :active")
    Page<Category> findAllByActive(boolean active, Pageable pageable);

    @Query("select c from Category c where c.active = :active and c.id = :id")
    Optional<Category> findByActiveAndId(boolean active, Long id);

    @Query("select c from Category c where c.active = :active and c.code = :code")
    Category findByActiveAndCode(boolean active, String code);

    @Query("select c from Category c where c.active = :active and c.name = :name")
    Category findByActiveAndName(boolean active, String name);

    @Query("select c from Category c where c.active = :active and c.code = :code and c.id <> :id")
    Category findByActiveAndCodeAndIdNot(boolean active, String code, Long id);
}
