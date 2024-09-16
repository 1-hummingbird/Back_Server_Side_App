package com.hummingbird.kr.starbuckslike.review.application;

import com.hummingbird.kr.starbuckslike.review.dto.out.ReviewListImageResponseDto;
import com.hummingbird.kr.starbuckslike.review.dto.out.ReviewListInfoResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ReviewService {
    /**
     * Select
     */
    Slice<Long> searchReviewListById(Pageable pageable, Long productId);
    // 리뷰 이미지 조회
    ReviewListImageResponseDto findReviewImageById(Long reviewId);
    // 리뷰 내용 조회
    ReviewListInfoResponseDto findReviewInfoById(Long reviewId);

}
