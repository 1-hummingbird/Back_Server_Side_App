package com.hummingbird.kr.starbuckslike.auth.dto.in;

import com.hummingbird.kr.starbuckslike.auth.domain.OauthInfo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OauthRegisterRequestDTO {
    private String oauthID;
    private String oauthType;
    private String memberUID;
    private String oauthToken;

    public OauthRegisterRequestDTO(String oauthID, String oauthType, String memberUID, String oauthToken) {
        this.oauthID = oauthID;
        this.oauthType = oauthType;
        this.memberUID = memberUID;
        this.oauthToken = oauthToken;
    }

    public OauthInfo toEntity() {
        return new OauthInfo(this.oauthID, this.oauthType, this.memberUID);
    }
}
