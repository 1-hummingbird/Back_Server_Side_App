package com.hummingbird.kr.starbuckslike.member.vo.in;

import com.hummingbird.kr.starbuckslike.member.dto.in.CanReviewRequestDTO;
import lombok.Getter;

@Getter
public class CanReviewRequestVO {
    private String memberUID;

    public CanReviewRequestDTO toDTO() {
        return new CanReviewRequestDTO(memberUID);
    }
}
