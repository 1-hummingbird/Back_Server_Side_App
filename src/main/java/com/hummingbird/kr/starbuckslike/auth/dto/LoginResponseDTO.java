package com.hummingbird.kr.starbuckslike.auth.dto;

public class LoginResponseDTO {
    private String accessToken;
    public LoginResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}