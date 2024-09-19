package com.hummingbird.kr.starbuckslike.auth.dto.in;

import lombok.Getter;

@Getter
public class CheckLoginIDRequestDTO {
    private String loginID;

    public CheckLoginIDRequestDTO(String loginID) {
        this.loginID = loginID;
    }
}
