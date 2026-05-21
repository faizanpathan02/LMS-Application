package com.lms.main.controller;


import com.lms.main.dto.UserResponse;
import com.lms.main.entity.User;
import com.lms.main.service.UserDetailsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.lms.main.LmsApplication.LOGGER;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDetailsService userDetailsService;


    @PostMapping(value = "/register" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> registerUser(@RequestBody User user){
        LOGGER.info("========================== User Controller Data Insertion Start ============================");
        ResponseEntity<UserResponse> response = userDetailsService.registerUser(user);
        LOGGER.info("========================== User Controller Data Insertion End ============================");

        return response;
    }
}
