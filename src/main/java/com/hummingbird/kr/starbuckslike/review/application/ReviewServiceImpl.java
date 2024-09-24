package com.hummingbird.kr.starbuckslike.review.application;


import com.hummingbird.kr.starbuckslike.common.Exception.BaseException;
import com.hummingbird.kr.starbuckslike.common.entity.BaseResponseStatus;
import com.hummingbird.kr.starbuckslike.purchase.domain.PurchaseProduct;
import com.hummingbird.kr.starbuckslike.purchase.infrastructure.PurchaseProductRepository;
import com.hummingbird.kr.starbuckslike.purchase.infrastructure.search.PurchaseSearch;
import com.hummingbird.kr.starbuckslike.redis.facade.ReviewLockFacade;
import com.hummingbird.kr.starbuckslike.review.domain.Review;
import com.hummingbird.kr.starbuckslike.review.domain.ReviewComment;
import com.hummingbird.kr.starbuckslike.review.dto.in.*;
import com.hummingbird.kr.starbuckslike.review.dto.out.*;
import com.hummingbird.kr.starbuckslike.review.infrastructure.ReviewCommentRepository;
import com.hummingbird.kr.starbuckslike.review.infrastructure.ReviewImageRepository;
import com.hummingbird.kr.starbuckslike.review.infrastructure.ReviewRepository;
import com.hummingbird.kr.starbuckslike.review.infrastructure.condition.ReviewCondition;
import com.hummingbird.kr.starbuckslike.review.infrastructure.search.ReviewSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReviewServiceImpl implements ReviewService{
    private final ReviewSearch reviewSearch;
    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final PurchaseSearch purchaseSearch;
    private final ReviewCommentRepository reviewCommentRepository;
    private final PurchaseProductRepository purchaseProductRepository;
    @Override
    public Slice<Long> searchReviewListById(Pageable pageable, Long productId, ReviewCondition reviewCondition) {
        return reviewSearch.searchReviewListById(pageable, productId, reviewCondition);
    }

    @Override
    public Slice<Long> searchReviewListByMemberUuid(Pageable pageable, String memberUID) {
        return reviewSearch.searchReviewListByMemberUuid(pageable,memberUID);
    }

    @Override
    public ReviewListImageResponseDto findReviewImageById(Long reviewId) {
        return reviewSearch.findReviewImageById(reviewId);
    }

    @Override
    public ReviewListInfoResponseDto findReviewInfoById(Long reviewId) {
        return reviewSearch.findReviewInfoById(reviewId);
    }

    @Override
    public List<ReviewCommentResponseDto> findReviewCommentById(Long reviewId) {
        return reviewSearch.findReviewCommentById(reviewId);
    }

    @Override
    public ReviewSummaryResponseDto findReviewSummaryDtoById(Long productId) {
        return reviewSearch.findReviewSummaryDtoById(productId);
    }

    @Override
    public void increaseCommentCountWithLock(AddReviewCommentRequestDto dto) {
        // 비관적 락을 사용하여 리뷰 조회
        Review review = reviewRepository.findByIdWithPessimisticLock(dto.getReviewId());
        // 댓글 달기
        reviewCommentRepository.save(dto.toReviewComment());
        // 리뷰 댓글카운트 +1 업데이트
        review.increaseCommentCount();
        reviewRepository.save(review);
    }

    @Override
    public void addReview(AddReviewRequestDto dto) {
        // 정상적인 리뷰 저장인지 확인
        checkValidReview(dto);
        // 리뷰 생성
        Review review = dto.toReview();
        reviewRepository.save(review);
        // 리뷰 이미지 생성
        reviewImageRepository.saveAll(dto.toReviewImage(review));
        // 주문상품에 리뷰 작성되었다 처리
        purchaseProductRepository.updatePurchaseProductReview(dto.getPurchaseProductId());
    }

    private void checkValidReview(AddReviewRequestDto dto) {
        // 1. 유효한 주문코드 확인
        if(!purchaseSearch.exists(dto.getPurchaseCode())){
            throw new BaseException(BaseResponseStatus.NO_EXIST_INTEREST);
        }
        // 2. 이미 해당 주문에 상품옵션 리뷰가 있는지 확인
        Boolean reviewExists = reviewSearch.exists(dto.getMemberUID(),
                                            dto.getPurchaseCode(),
                                            dto.getOptionId());
        if(reviewExists){
            throw new BaseException(BaseResponseStatus.DISALLOWED_ACTION);
        }
        // 3. 리뷰 이미지 최대 개수 확인
        if(dto.getReviewImages().size() > 5){
            throw new BaseException(BaseResponseStatus.DISALLOWED_ACTION);
        }
    }


    @Override
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_INTEREST));
        reviewRepository.softDeleteReview(reviewId);

    }

    @Override
    public void addReviewComment(AddReviewCommentRequestDto addReviewCommentRequestDto) {
        Review review = reviewRepository.findById(addReviewCommentRequestDto.getReviewId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_INTEREST));

        // 댓글 달기
        reviewCommentRepository.save(addReviewCommentRequestDto.toReviewComment());
        // 리뷰 댓글카운트 +1 업데이트
        review.increaseCommentCount();
        reviewRepository.save(review);
    }


    @Override
    public void deleteReviewComment(DeleteReviewCommentRequestDto dto) {
        ReviewComment reviewComment = reviewCommentRepository.findById(dto.getReviewCommentId()).
            orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_INTEREST));
        // 작성자가 입력한 댓글이라면 삭제
        if(Objects.equals(reviewComment.getMemberUID(), dto.getMemberUID())) {
            reviewCommentRepository.delete(reviewComment);
        }
        Review review = reviewRepository.findById(reviewComment.getReviewId())
            .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_INTEREST));
        // 리뷰 댓글카운트 -1 업데이트
        review.decreaseCommentCount();
        if(review.getCommentCount() < 0){
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
        reviewRepository.save(review);
        log.info("리뷰댓글 서비스 실행됨 ");
    }

}
