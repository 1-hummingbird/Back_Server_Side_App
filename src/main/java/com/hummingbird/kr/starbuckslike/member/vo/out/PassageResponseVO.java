package com.hummingbird.kr.starbuckslike.member.vo.out;

import lombok.Getter;

@Getter
public class PassageResponseVO {
    private String memberUID;
    private long dateDiff;

    public PassageResponseVO(String memberUID, long dateDiff) {
        this.memberUID = memberUID;
        this.dateDiff = dateDiff;
    }
}
