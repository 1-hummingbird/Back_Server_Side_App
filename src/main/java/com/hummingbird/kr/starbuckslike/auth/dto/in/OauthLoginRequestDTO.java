package com.hummingbird.kr.starbuckslike.auth.dto.in;

import lombok.Getter;

@Getter 
public class OauthLoginRequestDTO {
    private String oauthID;
    private String oauthType;

    public OauthLoginRequestDTO(String oauthID, String oauthType) {
        this.oauthID = oauthID;
        this.oauthType = oauthType;
    }
}
