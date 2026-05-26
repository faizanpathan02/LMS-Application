package com.lms.main.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserUpdateRequest {

    private String fname;

    private String lname;

    private String password;

    private String email;

    @Column(name = "contact_no")
    private String contactNo;

    private String uname;


}
