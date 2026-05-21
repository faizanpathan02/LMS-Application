package com.lms.main.service;

import com.lms.main.dto.UserResponse;
import com.lms.main.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserDetailsService {
    ResponseEntity<UserResponse> registerUser(User user);
}
