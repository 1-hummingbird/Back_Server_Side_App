package com.hummingbird.kr.starbuckslike.auth.dto.in;

import lombok.Getter;

@Getter
public class EmailVerificationCheckRequestDTO {
    private String email;
    private String verificationCode;

    public EmailVerificationCheckRequestDTO(String email, String verificationCode) {
        this.email = email;
        this.verificationCode = verificationCode;
    }
}