package com.hummingbird.kr.starbuckslike.batch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewStarDto {
    private Long productId;
    private Long reviewCount;
    private Double averageStar;
    private Long photoReviewCount;
    private Long totalStar;

}