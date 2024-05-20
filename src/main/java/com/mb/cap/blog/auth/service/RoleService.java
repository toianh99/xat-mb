package com.mb.cap.blog.auth.service;

import com.mb.cap.blog.auth.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAllByUserId(String userId);
}
