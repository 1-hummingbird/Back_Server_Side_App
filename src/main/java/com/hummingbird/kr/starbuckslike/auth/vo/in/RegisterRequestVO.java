package com.hummingbird.kr.starbuckslike.auth.vo.in;

import com.hummingbird.kr.starbuckslike.auth.dto.in.RegisterRequestDTO;
import lombok.Getter;

import java.util.Date;

@Getter
public class RegisterRequestVO {

    private String loginID;
    private String name;
    private String nickname;
    private Date birthdate;
    private String phone;
    private String email;
    private String password;

    public RegisterRequestDTO toDTO() {
        return RegisterRequestDTO.builder()
                .loginID(loginID)
                .name(name)
                .nickname(nickname)
                .birth(birthdate)
                .phone(phone)
                .email(email)
                .password(password)
                .build();
    }
}
