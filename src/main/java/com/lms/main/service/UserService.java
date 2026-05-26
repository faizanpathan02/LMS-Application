package com.lms.main.service;

import com.lms.main.dto.UserDTOResponse;
import com.lms.main.dto.UserResponse;
import com.lms.main.dto.UserUpdateRequest;
import com.lms.main.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<UserResponse> registerUser(User user);

    UserDTOResponse findUserByUsername(String uname);

    User updateUser(Integer id , UserUpdateRequest request);

    String login(String usernameOrEmail , String password);
}
