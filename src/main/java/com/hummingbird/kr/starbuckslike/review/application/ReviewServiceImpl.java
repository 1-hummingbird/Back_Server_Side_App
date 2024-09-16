package com.hummingbird.kr.starbuckslike.review.application;


import com.hummingbird.kr.starbuckslike.review.dto.out.ReviewListImageResponseDto;
import com.hummingbird.kr.starbuckslike.review.dto.out.ReviewListInfoResponseDto;
import com.hummingbird.kr.starbuckslike.review.infrastructure.search.ReviewSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private final ReviewSearch reviewSearch;
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
}
