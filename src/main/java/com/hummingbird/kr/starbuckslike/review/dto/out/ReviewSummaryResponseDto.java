package com.hummingbird.kr.starbuckslike.review.dto.out;

import com.hummingbird.kr.starbuckslike.review.vo.out.ReviewSummaryResponseVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewSummaryResponseDto {
    private Long reviewCount; // 총 리뷰 개수
    private Long photoReviewCount; // 총 포토 리뷰 수
    private Double averageStar; // 평균 별점

    public ReviewSummaryResponseVo toVo(){
        return ReviewSummaryResponseVo.builder()
                .reviewCount(reviewCount)
                .photoReviewCount(photoReviewCount)
                .averageStar(averageStar)
                .build();
    }

    @QueryProjection
    public ReviewSummaryResponseDto(Long reviewCount, Long photoReviewCount, Double averageStar) {
        this.reviewCount = reviewCount;
        this.photoReviewCount = photoReviewCount;
        this.averageStar = averageStar;
    }
}
