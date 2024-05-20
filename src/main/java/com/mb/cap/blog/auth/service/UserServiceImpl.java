package com.mb.cap.blog.auth.service;

import com.mb.cap.blog.auth.dto.UserDto;
import com.mb.cap.blog.auth.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDto findByUsername(String username) {
        return null;
    }
}
