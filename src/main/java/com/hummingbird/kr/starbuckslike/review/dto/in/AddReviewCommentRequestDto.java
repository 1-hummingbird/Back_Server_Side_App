package com.hummingbird.kr.starbuckslike.review.dto.in;

import com.hummingbird.kr.starbuckslike.review.domain.Review;
import com.hummingbird.kr.starbuckslike.review.domain.ReviewComment;
import com.hummingbird.kr.starbuckslike.review.vo.in.AddReviewCommentRequestVo;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddReviewCommentRequestDto {
    private Long reviewId;

    private String nickname;

    private String memberUID;

    private String content;

    // 리뷰 댓글 엔티티 생성
    public ReviewComment toReviewComment() {
        return ReviewComment.builder()
                .reviewId(reviewId)
                .nickname(nickname)
                .memberUID(memberUID)
                .content(content)
                .build();
    }

    // vo -> dto
    public static AddReviewCommentRequestDto of(AddReviewCommentRequestVo vo) {
        return AddReviewCommentRequestDto.builder()
                .reviewId(vo.getReviewId())
                .nickname(vo.getNickname())
                .memberUID(vo.getMemberUID())
                .content(vo.getContent())
                .build();
    }

}
