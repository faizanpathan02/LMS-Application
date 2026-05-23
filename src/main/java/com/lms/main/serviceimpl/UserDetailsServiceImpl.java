package com.lms.main.serviceimpl;

import com.lms.main.dao.UserDao;
import com.lms.main.dto.UserDTOResponse;
import com.lms.main.dto.UserResponse;
import com.lms.main.entity.User;
import com.lms.main.service.UserDetailsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserDao userDao;

    public static final Logger LOGGER = LogManager.getLogger(UserDetailsServiceImpl.class);

    @Override
    public ResponseEntity<UserResponse> registerUser(User user) {
        LOGGER.info("==========================User Service Data Insertion Start============================");
        UserResponse userResponse = new UserResponse();
        User user1 = userDao.save(user);

        if (user != null){
            userResponse.setMsg(user1.getFname() + " " + user1.getLname() + ", Registered Successfully...!");
            return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
        }
        LOGGER.info("==========================User Service Data Insertion End=============================");
        userResponse.setMsg("Registration Failed....!");
        return new ResponseEntity<>(userResponse, HttpStatus.NO_CONTENT);
    }

    @Override
    public UserDTOResponse findUserByUsername(String uname) {
        LOGGER.info("==========================User Service Data Fetch Start=============================");
        User user = userDao.findByUname(uname);
        UserDTOResponse dto = new UserDTOResponse();
        if (!ObjectUtils.isEmpty(user)){
            return dto.userToUserDTOResponseConversion(user);
        }
        LOGGER.info("==========================User Service Data Fetch End=============================");
        dto.setErrorMsg("User Not Found.......!");
        return dto;
    }
}
