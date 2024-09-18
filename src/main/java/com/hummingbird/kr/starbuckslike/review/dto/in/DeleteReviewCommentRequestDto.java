package com.hummingbird.kr.starbuckslike.review.dto.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteReviewCommentRequestDto {
    private Long reviewCommentId;
    private String memberUid;
}