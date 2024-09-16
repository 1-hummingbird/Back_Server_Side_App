package com.hummingbird.kr.starbuckslike.auth.vo.in;

import com.hummingbird.kr.starbuckslike.auth.dto.in.EmailVerificationRequestDTO;

import lombok.Getter;

@Getter
public class EmailVerificationRequestVO {
    private String email;

    public EmailVerificationRequestDTO toDTO() {
        return new EmailVerificationRequestDTO(this.email);
    }
}
