package com.hummingbird.kr.starbuckslike.auth.vo.in;

import com.hummingbird.kr.starbuckslike.auth.dto.in.OauthLoginRequestDTO;

public class OauthLoginRequestVO {
    private String oauthID;
    private String oauthType;

    public OauthLoginRequestDTO toDTO() {
        return new OauthLoginRequestDTO(this.oauthID, this.oauthType);
    }
}
