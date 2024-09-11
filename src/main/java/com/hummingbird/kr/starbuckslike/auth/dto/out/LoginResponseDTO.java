package com.hummingbird.kr.starbuckslike.auth.dto.out;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {
    private String accessToken;
    //    private String refreshToken;
    private String name;
    private String uuid;

}