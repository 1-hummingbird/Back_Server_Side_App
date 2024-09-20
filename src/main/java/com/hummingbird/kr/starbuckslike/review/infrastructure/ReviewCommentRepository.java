package com.hummingbird.kr.starbuckslike.review.infrastructure;

import com.hummingbird.kr.starbuckslike.review.domain.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ReviewCommentRepository extends JpaRepository<ReviewComment,Long> {

    // 리뷰 댓글 ID로 리뷰 ID를 조회하는 메서드
    @Query("SELECT rc.reviewId FROM ReviewComment rc WHERE rc.id = :reviewCommentId")
    Long findReviewIdByReviewCommentId(@Param("reviewCommentId") Long reviewCommentId);

}

