package com.hummingbird.kr.starbuckslike.auth.dto.in;

import com.hummingbird.kr.starbuckslike.auth.domain.OauthInfo;

import lombok.Getter;

@Getter
public class OauthRegisterRequestDTO {
    private String oauthID;
    private String oauthType;
    private String memberUID;

    public OauthRegisterRequestDTO(String oauthID, String oauthType, String memberUID) {
        this.oauthID = oauthID;
        this.oauthType = oauthType;
        this.memberUID = memberUID;
    }

    public OauthInfo toEntity() {
        return new OauthInfo(this.oauthID, this.oauthType, this.memberUID);
    }
}
