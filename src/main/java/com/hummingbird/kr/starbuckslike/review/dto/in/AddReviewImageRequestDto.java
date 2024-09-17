package com.hummingbird.kr.starbuckslike.review.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddReviewImageRequestDto {
    private Integer seq;

    private String imageUrl;
}
