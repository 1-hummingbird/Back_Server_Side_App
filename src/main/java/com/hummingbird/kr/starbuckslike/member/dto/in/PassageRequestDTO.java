package com.hummingbird.kr.starbuckslike.member.dto.in;

public class PassageRequestDTO {
    private String memberUID;

    public String getMemberUID() {
        return memberUID;
    }

    public PassageRequestDTO(String memberUID) {
        this.memberUID = memberUID;
    }
}
