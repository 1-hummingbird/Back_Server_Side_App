package com.hummingbird.kr.starbuckslike.review.dto.in;

import com.hummingbird.kr.starbuckslike.review.vo.in.DeleteReviewCommentRequestVo;
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
    private String memberUID;

    public static DeleteReviewCommentRequestDto of(DeleteReviewCommentRequestVo vo, String memberUID) {
        return DeleteReviewCommentRequestDto.builder()
                .reviewCommentId(vo.getReviewCommentId())
                .memberUID(memberUID)
                .build();
    }
}
