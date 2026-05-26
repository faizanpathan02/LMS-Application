package com.lms.main.serviceimpl;

import com.lms.main.dao.UserDao;
import com.lms.main.dto.UserDTOResponse;
import com.lms.main.dto.UserResponse;
import com.lms.main.dto.UserUpdateRequest;
import com.lms.main.entity.User;
import com.lms.main.enums.UserStatus;
import com.lms.main.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao;

    public static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);





    @Override
    public ResponseEntity<UserResponse> registerUser(User user) {

        LOGGER.info("==========================User Service Data Insertion Start============================");
        UserResponse userResponse = new UserResponse();

        if (userDao.findByUname(user.getUname()) != null){
            throw new RuntimeException("Username already exists");
        }

        user.setStatus(UserStatus.ACTIVE);
        user.setUserCode(generateUserCode());
        User savedUser = userDao.save(user);

        if (savedUser != null){
            userResponse.setMsg(savedUser.getFname() + " " + savedUser.getLname() + ", Registered Successfully...!");
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









    @Override
    public User updateUser(Integer id, UserUpdateRequest request) {
        User existingUser = userDao.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with Id : " + id));

        existingUser.setFname(request.getFname());
        existingUser.setLname(request.getLname());
        existingUser.setEmail(request.getEmail());
        existingUser.setContactNo(request.getContactNo());
        existingUser.setUname(request.getUname());

        return userDao.save(existingUser);
    }










    @Override
    public String login(String usernameOrEmail, String password) {

        User user = userDao.findByUnameOrEmail(usernameOrEmail , usernameOrEmail)
                .orElseThrow(()->new RuntimeException("Invalid Username/Email"));

        if (user.getStatus() != UserStatus.ACTIVE){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User is not Active");
        }

        if (!user.getPassword().equals(password)){
            throw new RuntimeException("Invalid Password");
        }

        return "Login Successful";
    }







    public String generateUserCode(){

        String lastCode = userDao.findLastUserCode();

        if (lastCode == null){
            return "LMS001";
        }

        int num = Integer.parseInt(lastCode.substring(3));
        num++;

        return String.format("LMS%03d" , num);
    }


}
