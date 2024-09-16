package com.hummingbird.kr.starbuckslike.auth.vo.out;

import lombok.Getter;

@Getter
public class CheckEmailResponseVO {
    private boolean isAvailable;

    public CheckEmailResponseVO(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}