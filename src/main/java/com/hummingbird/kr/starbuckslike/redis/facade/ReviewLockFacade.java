package com.hummingbird.kr.starbuckslike.redis.facade;

import com.hummingbird.kr.starbuckslike.redis.infrastructure.RedisLockRepository;
import com.hummingbird.kr.starbuckslike.review.application.ReviewService;
import com.hummingbird.kr.starbuckslike.review.dto.in.AddReviewCommentRequestDto;
import com.hummingbird.kr.starbuckslike.review.dto.in.DeleteReviewCommentRequestDto;
import com.hummingbird.kr.starbuckslike.review.infrastructure.ReviewCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewLockFacade {

    private final RedisLockRepository redisLockRepository;

    private final ReviewService reviewService;

    private final ReviewCommentRepository reviewCommentRepository;

    // 리뷰 댓글 추가 시 리뷰에 대한 락 처리
    public void addReviewCommentAndIncreaseCount(AddReviewCommentRequestDto dto) throws InterruptedException {
        Long reviewId = dto.getReviewId(); // 리뷰 Id
        while (!redisLockRepository.reviewLock(reviewId)) {
            Thread.sleep(100);
        }
        try {
            reviewService.addReviewComment(dto);
        } finally {
            redisLockRepository.reviewUnlock(reviewId);
        }
    }

    // 리뷰 댓글 삭제 시 리뷰에 대한 락 처리
    public void deleteReviewAndDecreaseCount(DeleteReviewCommentRequestDto dto) throws InterruptedException {
        // 댓글 ID로 리뷰 ID 조회
        Long reviewId = reviewCommentRepository.findReviewIdByReviewCommentId(dto.getReviewCommentId());

        // 리뷰 ID로 락을 걸어 동시성 처리
        while (!redisLockRepository.reviewLock(reviewId)) {
            Thread.sleep(100);
        }
        try {
            reviewService.deleteReviewComment(dto);
        } finally {
            redisLockRepository.reviewUnlock(reviewId);
        }
    }




}