package com.mb.cap.blog.auth.repo;

import com.mb.cap.blog.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "SELECT r FROM xat_role r inner join xat_user_role u on u.user_id = :userId and u.active = true", nativeQuery = true)
    List<Role> findAllByUserId(String userId);
}