package com.hummingbird.kr.starbuckslike.auth.vo.in;

import com.hummingbird.kr.starbuckslike.auth.dto.in.PhoneVerificationRequestDTO;

import lombok.Getter;

@Getter
public class PhoneVerificationRequestVO {
    private String phoneNumber;

    public PhoneVerificationRequestDTO toDTO() {
        return new PhoneVerificationRequestDTO(this.phoneNumber);
    }
}
