package com.hummingbird.kr.starbuckslike.auth.dto.in;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OauthLoginRequestDTO {
    private String oauthID;
    private String oauthType;
    private String oauthToken;

    public OauthLoginRequestDTO(String oauthID, String oauthType, String oauthToken) {
        this.oauthID = oauthID;
        this.oauthType = oauthType;
        this.oauthToken = oauthToken;
    }
}
