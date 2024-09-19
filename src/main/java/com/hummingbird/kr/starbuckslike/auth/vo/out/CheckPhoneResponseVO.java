package com.hummingbird.kr.starbuckslike.auth.vo.out;

import lombok.Getter;

@Getter
public class CheckPhoneResponseVO {
    private boolean isAvailable;

    public CheckPhoneResponseVO(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}


