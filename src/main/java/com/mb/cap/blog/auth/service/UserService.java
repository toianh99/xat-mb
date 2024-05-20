package com.mb.cap.blog.auth.service;

import com.mb.cap.blog.auth.dto.UserDto;

public interface UserService {
    UserDto findByUsername(String username);
}
