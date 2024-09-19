package com.hummingbird.kr.starbuckslike.auth.dto.in;

import lombok.Getter;

@Getter
public class LogoutRequestDTO {
    public LogoutRequestDTO(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
    private String accessToken;
    private String refreshToken;
}
