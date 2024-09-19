package com.hummingbird.kr.starbuckslike.review.dto.out;

import com.hummingbird.kr.starbuckslike.review.vo.out.ReviewListImageResponseVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ReviewListImageResponseDto {
    private List<String> imageUrl;
    public ReviewListImageResponseVo toVo(){
        return ReviewListImageResponseVo.builder().imageUrl(imageUrl).build();
    }
}
