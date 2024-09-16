package com.hummingbird.kr.starbuckslike.auth.vo.out;

import lombok.Getter;

@Getter
public class FindIDResponseVO {
    private String loginID;

    public FindIDResponseVO(String loginID) {
        this.loginID = loginID;
    }
}
