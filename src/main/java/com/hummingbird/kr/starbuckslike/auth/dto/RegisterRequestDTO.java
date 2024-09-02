package com.hummingbird.kr.starbuckslike.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterRequestDTO {

    private String loginID;
    private String name;
    private String nickname;
    private String birthdate;
    private String phone;
    private String email;
    private String password;
    
}
