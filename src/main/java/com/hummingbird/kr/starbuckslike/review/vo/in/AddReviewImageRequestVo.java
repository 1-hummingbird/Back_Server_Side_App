package com.hummingbird.kr.starbuckslike.review.vo.in;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddReviewImageRequestVo {
    private Integer seq;

    private String imageUrl;
}
