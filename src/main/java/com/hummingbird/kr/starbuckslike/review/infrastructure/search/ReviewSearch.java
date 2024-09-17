package com.hummingbird.kr.starbuckslike.review.infrastructure.search;

import com.hummingbird.kr.starbuckslike.review.dto.out.ReviewListImageResponseDto;
import com.hummingbird.kr.starbuckslike.review.dto.out.ReviewListInfoResponseDto;
import com.hummingbird.kr.starbuckslike.review.vo.out.ReviewListImageResponseVo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface ReviewSearch {

    /**
     * 리뷰 페이징
     */
    // 리뷰 id 리스트 조회
    Slice<Long> searchReviewListById(Pageable pageable, Long productId);
    // 리뷰 이미지 조회
    ReviewListImageResponseDto findReviewImageById(Long reviewId);
    // 리뷰 내용 조회
    ReviewListInfoResponseDto findReviewInfoById(Long reviewId);

    // 이미 작성된 리뷰인지 확인
    Boolean exists(String memberUuid, String purchaseCode, Long optionId);

}
