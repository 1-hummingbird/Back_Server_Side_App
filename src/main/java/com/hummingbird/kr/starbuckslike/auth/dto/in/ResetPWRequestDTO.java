package com.hummingbird.kr.starbuckslike.auth.dto.in;

import lombok.Getter;

@Getter
public class ResetPWRequestDTO {
    public ResetPWRequestDTO(String loginID, String password) {
        this.loginID = loginID;
        this.password = password;
    }
    private String loginID;
    private String password;
}
