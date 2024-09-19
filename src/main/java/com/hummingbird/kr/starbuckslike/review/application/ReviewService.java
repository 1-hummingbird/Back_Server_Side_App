package com.hummingbird.kr.starbuckslike.review.application;

import com.hummingbird.kr.starbuckslike.review.dto.in.AddReviewCommentRequestDto;
import com.hummingbird.kr.starbuckslike.review.dto.in.AddReviewRequestDto;
import com.hummingbird.kr.starbuckslike.review.dto.in.DeleteReviewCommentRequestDto;
import com.hummingbird.kr.starbuckslike.review.dto.out.ReviewCommentResponseDto;
import com.hummingbird.kr.starbuckslike.review.dto.out.ReviewListImageResponseDto;
import com.hummingbird.kr.starbuckslike.review.dto.out.ReviewListInfoResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface ReviewService {
    /**
     * Select
     */
    Slice<Long> searchReviewListById(Pageable pageable, Long productId);
    Slice<Long> searchReviewListByMemberUuid(Pageable pageable, String memberUuid);
    // 리뷰 이미지 조회
    ReviewListImageResponseDto findReviewImageById(Long reviewId);
    // 리뷰 내용 조회
    ReviewListInfoResponseDto findReviewInfoById(Long reviewId);

    // 리뷰 댓글 조회
    List<ReviewCommentResponseDto> findReviewCommentById(Long reviewId);

    /**
     * Create , Update , Delete
     */
    // 리뷰 작성
    @Transactional
    void addReview(AddReviewRequestDto addReviewRequestDto);

    // 리뷰 삭제
    @Transactional
    void deleteReview(Long reviewId);

    // 리뷰 댓글달기
    @Transactional
    void addReviewComment(AddReviewCommentRequestDto addReviewCommentRequestDto);

    // 리뷰 댓글 삭제 (hard delete)
    @Transactional
    void deleteReviewComment(DeleteReviewCommentRequestDto dto);
}
