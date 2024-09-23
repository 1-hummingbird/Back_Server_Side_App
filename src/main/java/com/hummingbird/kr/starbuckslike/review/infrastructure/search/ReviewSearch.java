package com.hummingbird.kr.starbuckslike.review.infrastructure.search;

import com.hummingbird.kr.starbuckslike.review.dto.out.ReviewCommentResponseDto;
import com.hummingbird.kr.starbuckslike.review.dto.out.ReviewListImageResponseDto;
import com.hummingbird.kr.starbuckslike.review.dto.out.ReviewListInfoResponseDto;
import com.hummingbird.kr.starbuckslike.review.infrastructure.condition.ReviewCondition;
import com.hummingbird.kr.starbuckslike.review.vo.out.ReviewListImageResponseVo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface ReviewSearch {

    /**
     * 상품의 리뷰 페이징
     */
    // 리뷰 id 리스트 조회
    Slice<Long> searchReviewListById(Pageable pageable, Long productId , ReviewCondition reviewCondition);
    // 리뷰 이미지 조회
    ReviewListImageResponseDto findReviewImageById(Long reviewId);
    // 리뷰 내용 조회
    ReviewListInfoResponseDto findReviewInfoById(Long reviewId);
    /**
     * 회원이 쓴 리뷰 페이징
     */
    // 회원이 쓴 리뷰
    Slice<Long> searchReviewListByMemberUuid(Pageable pageable, String memberUID);

    // 이미 작성된 리뷰인지 확인
    Boolean exists(String memberUuid, String purchaseCode, Long optionId);

    // 리뷰 댓글 보기 (stream 정렬)
    List<ReviewCommentResponseDto> findReviewCommentById(Long reviewId);

    // 리뷰 댓글 보기 (order by)
    List<ReviewCommentResponseDto> findReviewCommentByIdTest(Long reviewId);
}
