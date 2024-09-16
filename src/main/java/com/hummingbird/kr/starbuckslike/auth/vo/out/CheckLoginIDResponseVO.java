package com.hummingbird.kr.starbuckslike.auth.vo.out;

import lombok.Getter;

@Getter
public class CheckLoginIDResponseVO {
    private boolean isAvailable;

    public CheckLoginIDResponseVO(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
