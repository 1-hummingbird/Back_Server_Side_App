package com.hummingbird.kr.starbuckslike.member.vo.in;

import com.hummingbird.kr.starbuckslike.member.dto.in.PassageRequestDTO;

public class PassageRequestVO {
    private String memberUID;

    public String getMemberUID() {
        return memberUID;
    }

    public PassageRequestDTO toDTO() {
        return new PassageRequestDTO(memberUID);
    }
}
