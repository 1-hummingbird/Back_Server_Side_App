package com.hummingbird.kr.starbuckslike.member.vo.out;

import lombok.Getter;

@Getter
public class PassageResponseVO {
    private long dateDiff;

    public PassageResponseVO(long dateDiff) {
        this.dateDiff = dateDiff;
    }
}
