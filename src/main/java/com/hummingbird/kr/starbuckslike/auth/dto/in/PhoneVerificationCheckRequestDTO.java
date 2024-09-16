package com.hummingbird.kr.starbuckslike.auth.dto.in;

import lombok.Getter;

@Getter
public class PhoneVerificationCheckRequestDTO {
    private String phoneNumber;
    private String verificationCode;

    public PhoneVerificationCheckRequestDTO(String phoneNumber, String verificationCode) {
        this.phoneNumber = phoneNumber;
        this.verificationCode = verificationCode;
    }   
}
