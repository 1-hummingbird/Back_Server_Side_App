package com.hummingbird.kr.starbuckslike.auth.vo.in;

import com.hummingbird.kr.starbuckslike.auth.dto.in.CheckLoginIDRequestDTO;
import lombok.Getter;

@Getter
public class CheckLoginIDRequestVO {
    private String loginID;

    public CheckLoginIDRequestVO(String loginID) {
        this.loginID = loginID;
    }

    public CheckLoginIDRequestDTO toDTO() {
        return new CheckLoginIDRequestDTO(this.loginID);
    }
}
