package com.lms.main.dto;

import com.lms.main.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTOResponse {
    private int userId;

    private String fname;

    private String lname;

    private String email;

    private String contactNo;

    private String uname;

    private String errorMsg;

    public UserDTOResponse userToUserDTOResponseConversion(User user){
        return UserDTOResponse.builder()
                .userId(user.getUserId())
                .fname(user.getFname())
                .lname(user.getLname())
                .email(user.getEmail())
                .contactNo(user.getContactNo())
                .uname(user.getUname())
                .build();
    }
}
