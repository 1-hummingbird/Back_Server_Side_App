package com.hummingbird.kr.starbuckslike.auth.vo.in;

import com.hummingbird.kr.starbuckslike.auth.dto.in.PhoneVerificationCheckRequestDTO;

import lombok.Getter;
@Getter
public class PhoneVerificationCheckRequestVO {
    private String phoneNumber;
    private String verificationCode;

    public PhoneVerificationCheckRequestDTO toDTO() {
        return new PhoneVerificationCheckRequestDTO(this.phoneNumber, this.verificationCode);
    }
}
