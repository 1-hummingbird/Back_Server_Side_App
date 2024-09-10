package com.hummingbird.kr.starbuckslike.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponseDTO {
    private String accessToken;
//    private String refreshToken;
//    private String name;
//    private String uuid;

    public LoginResponseDTO(String token){
        this. accessToken = token;
    }

}