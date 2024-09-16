package com.hummingbird.kr.starbuckslike.auth.vo.in;

import com.hummingbird.kr.starbuckslike.auth.dto.in.EmailVerificationCheckRequestDTO;

import lombok.Getter;

@Getter
public class EmailVerificationCheckRequestVO {
    private String email;
    private String verificationCode;

    public EmailVerificationCheckRequestDTO toDTO() {
        return new EmailVerificationCheckRequestDTO(this.email, this.verificationCode);
    }
}
