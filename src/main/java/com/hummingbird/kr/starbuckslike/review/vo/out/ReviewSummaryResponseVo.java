package com.hummingbird.kr.starbuckslike.review.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewSummaryResponseVo {
    private Long reviewCount; // 총 리뷰 개수
    private Long photoReviewCount; // 총 포토 리뷰 수
    private Double averageStar; // 평균 별점
}
