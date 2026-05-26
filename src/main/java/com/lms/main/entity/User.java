package com.lms.main.entity;


import com.lms.main.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String fname;

    private String lname;

    private String email;

    private String contactNo;

    private String uname;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "user_code" , unique = true)
    private String userCode;

}
