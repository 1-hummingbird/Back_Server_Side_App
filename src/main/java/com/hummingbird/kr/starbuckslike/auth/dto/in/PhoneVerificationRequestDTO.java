package com.hummingbird.kr.starbuckslike.auth.dto.in;

import lombok.Getter;

@Getter
public class PhoneVerificationRequestDTO {
    private String phoneNumber;

    public PhoneVerificationRequestDTO(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
