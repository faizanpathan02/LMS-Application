package com.lms.main.dao;

import com.lms.main.dto.UserDTOResponse;
import com.lms.main.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {

    User findByUname(String uname);

}
