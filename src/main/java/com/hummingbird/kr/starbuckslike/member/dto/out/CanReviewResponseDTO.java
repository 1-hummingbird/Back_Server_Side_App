package com.hummingbird.kr.starbuckslike.member.dto.out;

import java.util.List;

import com.hummingbird.kr.starbuckslike.member.vo.out.CanReviewResponseVO;

import lombok.Getter;

@Getter
public class CanReviewResponseDTO {
    private List<Long> canReviewList; // Purchased Product Code list

    public CanReviewResponseDTO(List<Long> canReviewList) {
        this.canReviewList = canReviewList;
    }

    public CanReviewResponseVO toVO() {
        return new CanReviewResponseVO(canReviewList);
    }
}
