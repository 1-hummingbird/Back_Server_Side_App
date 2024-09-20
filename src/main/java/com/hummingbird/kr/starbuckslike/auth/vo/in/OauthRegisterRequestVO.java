package com.hummingbird.kr.starbuckslike.auth.vo.in;

import com.hummingbird.kr.starbuckslike.auth.dto.in.OauthRegisterRequestDTO;

import lombok.Getter;

@Getter
public class OauthRegisterRequestVO {
    private String oauthID;
    private String oauthType;
    private String memberUID;

    public OauthRegisterRequestDTO toDTO() {
        return new OauthRegisterRequestDTO(this.oauthID, this.oauthType, this.memberUID);
    }
}
