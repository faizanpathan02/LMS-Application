package com.lms.main.dao;

import com.lms.main.dto.UserDTOResponse;
import com.lms.main.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Integer> {

    User findByUname(String uname);

    @Query(value = "SELECT user_code FROM user ORDER BY user_id DESC LIMIT 1", nativeQuery = true)
    String findLastUserCode();

    Optional<User> findByUnameOrEmail(String uname , String email);

}
