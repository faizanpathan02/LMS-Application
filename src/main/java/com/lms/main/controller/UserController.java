package com.lms.main.controller;


import com.lms.main.dto.LoginRequest;
import com.lms.main.dto.UserDTOResponse;
import com.lms.main.dto.UserResponse;
import com.lms.main.dto.UserUpdateRequest;
import com.lms.main.entity.User;
import com.lms.main.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    public static final Logger LOGGER = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> registerUser(@RequestBody User user){
        LOGGER.info("========================== User Controller Data Insertion Start ============================");
        ResponseEntity<UserResponse> response = userService.registerUser(user);
        LOGGER.info("========================== User Controller Data Insertion End ============================");

        return response;
    }


    @GetMapping("/get/{uname}")
    public ResponseEntity<UserDTOResponse> fetchUserByUsername(@PathVariable String uname){
        LOGGER.info("========================== User Controller Data Insertion Start ============================");
        UserDTOResponse response = userService.findUserByUsername(uname);
        LOGGER.info("========================== User Controller Data Insertion End ============================");

        if (response != null){
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id , @RequestBody UserUpdateRequest request){

        User updatedUser = userService.updateUser(id , request);

        return ResponseEntity.ok(
                updatedUser.getFname() + " Updated Successfully");
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request){
        String response = userService.login(request.getUsernameOrEmail() , request.getPassword());
        return ResponseEntity.ok(response);

    }


}
