package com.hummingbird.kr.starbuckslike.auth.dto.in;

import lombok.Getter;

@Getter
public class EmailVerificationRequestDTO {
    private String email;

    public EmailVerificationRequestDTO(String email) {
        this.email = email;
    }
}
