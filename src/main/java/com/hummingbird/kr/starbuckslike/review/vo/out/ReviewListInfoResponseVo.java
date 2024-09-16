package com.hummingbird.kr.starbuckslike.review.vo.out;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewListInfoResponseVo {
    private String nickName;

    private Integer star;

    private LocalDateTime createAt;

    private String content;

}
