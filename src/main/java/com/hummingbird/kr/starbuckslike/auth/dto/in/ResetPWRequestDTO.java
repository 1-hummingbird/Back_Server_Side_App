package com.hummingbird.kr.starbuckslike.auth.dto.in;

import lombok.Getter;

@Getter
public class ResetPWRequestDTO {
    public ResetPWRequestDTO(String loginID, String password, String email) {
        this.loginID = loginID;
        this.password = password;
        this.email = email;
    }
    private String loginID;
    private String password;
    private String email;
}
