package com.hummingbird.kr.starbuckslike.review.vo.in;

import lombok.Getter;

@Getter
public class AddReviewCommentRequestVo {
    private Long reviewId;

    private String nickname;

    private String memberUID;

    private String content;
}
