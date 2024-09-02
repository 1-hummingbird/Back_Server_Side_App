package com.hummingbird.kr.starbuckslike.auth.vo;

import com.hummingbird.kr.starbuckslike.auth.dto.RegisterRequestDTO;


public class RegisterRequestVO {

    private String loginID;
    private String name;
    private String nickname;
    private String birthdate;
    private String phone;
    private String email;
    private String password;

    public RegisterRequestDTO toDTO(){
        return new RegisterRequestDTO(this.loginID, this.name, this.nickname, this.birthdate, this.phone, this.email, this.password);
    }
}
