package com.mb.cap.blog.auth.service;

import com.mb.cap.blog.auth.entity.Role;
import com.mb.cap.blog.auth.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository repository;

    @Override
    public List<Role> findAllByUserId(String userId) {
        List<Role> roles = repository.findAllByUserId(userId);
        return roles;
    }
}
