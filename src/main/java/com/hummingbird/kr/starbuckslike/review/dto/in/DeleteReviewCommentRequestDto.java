package com.hummingbird.kr.starbuckslike.review.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteReviewCommentRequestDto {
    private Long reviewCommentId;
    private String memberUid;
}