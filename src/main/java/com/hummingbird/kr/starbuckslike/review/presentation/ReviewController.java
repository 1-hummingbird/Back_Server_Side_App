package com.hummingbird.kr.starbuckslike.review.presentation;

import com.hummingbird.kr.starbuckslike.common.entity.CommonResponseEntity;
import com.hummingbird.kr.starbuckslike.common.entity.CommonResponseMessage;
import com.hummingbird.kr.starbuckslike.purchase.dto.out.PurchaseListResponseDto;
import com.hummingbird.kr.starbuckslike.purchase.vo.out.PurchaseListResponseVo;
import com.hummingbird.kr.starbuckslike.review.application.ReviewService;
import com.hummingbird.kr.starbuckslike.review.dto.out.ReviewListImageResponseDto;
import com.hummingbird.kr.starbuckslike.review.dto.out.ReviewListInfoResponseDto;
import com.hummingbird.kr.starbuckslike.review.vo.out.ReviewListImageResponseVo;
import com.hummingbird.kr.starbuckslike.review.vo.out.ReviewListInfoResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
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



}
