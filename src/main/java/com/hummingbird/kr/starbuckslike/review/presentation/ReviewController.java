package com.hummingbird.kr.starbuckslike.review.presentation;

import com.hummingbird.kr.starbuckslike.common.entity.CommonResponseEntity;
import com.hummingbird.kr.starbuckslike.common.entity.CommonResponseMessage;
import com.hummingbird.kr.starbuckslike.review.application.ReviewService;
import com.hummingbird.kr.starbuckslike.review.dto.in.AddReviewRequestDto;
import com.hummingbird.kr.starbuckslike.review.vo.out.ReviewListImageResponseVo;
import com.hummingbird.kr.starbuckslike.review.vo.out.ReviewListInfoResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
public class ReviewController {
    private final ReviewService reviewService;

    @Operation(summary = "리뷰 리스트 조회 리스트 [페이징]", description = "상품 id 로 리뷰 리스트 조회 ")
    @GetMapping("/list/{productId}")
    public CommonResponseEntity<Slice<Long>> searchReviewListByIdV1(
            Pageable pageable, @PathVariable("productId") Long productId
    ){
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                reviewService.searchReviewListById(pageable, productId)
        );
    }

    @Operation(summary = "리뷰 이미지 조회", description = "상품 id 로 리뷰 이미지 조회 ")
    @GetMapping("/image/{productId}")
    public CommonResponseEntity<ReviewListImageResponseVo> findReviewImageByIdV1(
            @PathVariable("productId") Long productId
    ){
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                reviewService.findReviewImageById(productId).toVo()
        );
    }

    @Operation(summary = "리뷰 내용 조회", description = "상품 id 로 리뷰 내용 조회 ")
    @GetMapping("/info/{productId}")
    public CommonResponseEntity<ReviewListInfoResponseVo> findReviewInfoByIdV1(
            @PathVariable("productId") Long productId
    ){
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                reviewService.findReviewInfoById(productId).toVo()
        );
    }

    @Operation(summary = "리뷰 작성", description = "리뷰 작성 하기")
    @PostMapping("")
    public CommonResponseEntity<Void> addReviewV1(
            @RequestBody AddReviewRequestDto addReviewRequestDto
    ){
        reviewService.addReview(addReviewRequestDto);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                null
        );
    }

    @Operation(summary = "리뷰 삭제" , description = "리뷰 작성 하기(soft delete)")
    @PostMapping("/delete/{reviewId}")
    public CommonResponseEntity<Void> deleteReviewV1(@PathVariable("reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                null
        );
    }


}
