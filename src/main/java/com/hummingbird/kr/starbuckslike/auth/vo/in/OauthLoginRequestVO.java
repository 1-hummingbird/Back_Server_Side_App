package com.hummingbird.kr.starbuckslike.auth.vo.in;

import com.hummingbird.kr.starbuckslike.auth.dto.in.OauthLoginRequestDTO;

import lombok.Setter;

@Setter
public class OauthLoginRequestVO {

    private String oauthID;
    private String oauthType;
    private String oauthToken;

    public OauthLoginRequestDTO toDTO() {
        return OauthLoginRequestDTO.builder()
                .oauthID(this.oauthID)
                .oauthType(this.oauthType)
                .oauthToken(this.oauthToken)
                .build();
    }
}
