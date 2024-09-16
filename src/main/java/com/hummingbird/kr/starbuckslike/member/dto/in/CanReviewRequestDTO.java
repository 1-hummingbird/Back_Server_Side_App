package com.hummingbird.kr.starbuckslike.member.dto.in;

import lombok.Getter;

@Getter
public class CanReviewRequestDTO {
    private String memberUID;

    public CanReviewRequestDTO(String memberUID) {
        this.memberUID = memberUID;
    }
}
