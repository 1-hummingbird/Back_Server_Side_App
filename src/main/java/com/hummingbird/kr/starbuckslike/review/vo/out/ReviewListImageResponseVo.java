package com.hummingbird.kr.starbuckslike.review.vo.out;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ReviewListImageResponseVo {
    private List<String> imageUrl;
}
