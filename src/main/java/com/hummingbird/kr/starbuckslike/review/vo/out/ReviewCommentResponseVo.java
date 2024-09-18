package com.hummingbird.kr.starbuckslike.review.vo.out;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewCommentResponseVo {
    private String nickname;
    private String content;
    private LocalDateTime createAt;
}
