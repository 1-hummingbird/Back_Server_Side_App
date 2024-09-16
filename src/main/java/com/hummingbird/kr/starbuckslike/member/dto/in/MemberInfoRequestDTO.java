package com.hummingbird.kr.starbuckslike.member.dto.in;

import lombok.Getter;
@Getter
public class MemberInfoRequestDTO {
    private String memberUID;

    public MemberInfoRequestDTO(String memberUID) {
        this.memberUID = memberUID;
    }
}
