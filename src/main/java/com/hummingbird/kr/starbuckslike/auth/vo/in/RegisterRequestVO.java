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
        return new RegisterRequestDTO(
            this.loginID, this.name, this.nickname, this.password, this.phone, this.email, this.birthdate
        );
    }
}
