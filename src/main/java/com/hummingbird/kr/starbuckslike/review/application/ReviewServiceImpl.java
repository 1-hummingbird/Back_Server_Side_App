package com.hummingbird.kr.starbuckslike.review.application;


import com.hummingbird.kr.starbuckslike.purchase.infrastructure.search.PurchaseSearch;
import com.hummingbird.kr.starbuckslike.review.domain.Review;
import com.hummingbird.kr.starbuckslike.review.domain.ReviewImage;
import com.hummingbird.kr.starbuckslike.review.dto.in.AddReviewRequestDto;
import com.hummingbird.kr.starbuckslike.review.dto.out.ReviewListImageResponseDto;
import com.hummingbird.kr.starbuckslike.review.dto.out.ReviewListInfoResponseDto;
import com.hummingbird.kr.starbuckslike.review.infrastructure.ReviewImageRepository;
import com.hummingbird.kr.starbuckslike.review.infrastructure.ReviewRepository;
import com.hummingbird.kr.starbuckslike.review.infrastructure.search.ReviewSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private final ReviewSearch reviewSearch;
    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final PurchaseSearch purchaseSearch;
    @Override
    public Slice<Long> searchReviewListById(Pageable pageable, Long productId) {
        return reviewSearch.searchReviewListById(pageable, productId);
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
    public void addReview(AddReviewRequestDto addReviewRequestDto) {
        // 정상적인 리뷰 저장인지 확인
        checkValidReview(addReviewRequestDto);
        // 리뷰 생성
        Review review = addReviewRequestDto.toReview();
        reviewRepository.save(review);
        // 주문 상품 생성
        List<ReviewImage> reviewImage = addReviewRequestDto.toReviewImage(review);
        reviewImageRepository.saveAll(reviewImage);
    }

    private void checkValidReview(AddReviewRequestDto addReviewRequestDto) {
        // 1. 유효한 주문코드 확인
        if(!purchaseSearch.exists(addReviewRequestDto.getPurchaseCode())){
            throw new IllegalArgumentException("잘못된 주문 코드입니다");
        }
        // 2. 이미 해당 주문에 상품옵션 리뷰가 있는지 확인
        Boolean exists = reviewSearch.exists(addReviewRequestDto.getMemberUID(),
                                            addReviewRequestDto.getPurchaseCode(),
                                            addReviewRequestDto.getOptionId());
        if(exists){
            throw new IllegalArgumentException("상품리뷰는 한 주문에 하나의 리뷰만 작성이 가능합니다.");
        }
        // 3. 리뷰 이미지 최대 개수 확인
        if(addReviewRequestDto.getReviewImages().size() > 5){
            throw new IllegalArgumentException("최대 5개 까지의 리뷰 이미지를 등록할 수 있습니다.");
        }
    }

    @Override
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다."));
        reviewRepository.softDeleteReview(reviewId);

    }

}
