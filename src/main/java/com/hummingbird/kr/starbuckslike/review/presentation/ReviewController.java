package com.hummingbird.kr.starbuckslike.review.presentation;

import com.hummingbird.kr.starbuckslike.common.entity.CommonResponseEntity;
import com.hummingbird.kr.starbuckslike.common.entity.CommonResponseMessage;
import com.hummingbird.kr.starbuckslike.review.application.ReviewService;
import com.hummingbird.kr.starbuckslike.review.dto.in.AddReviewCommentRequestDto;
import com.hummingbird.kr.starbuckslike.review.dto.in.AddReviewRequestDto;
import com.hummingbird.kr.starbuckslike.review.dto.in.DeleteReviewCommentRequestDto;
import com.hummingbird.kr.starbuckslike.review.dto.out.ReviewCommentResponseDto;
import com.hummingbird.kr.starbuckslike.review.vo.in.AddReviewCommentRequestVo;
import com.hummingbird.kr.starbuckslike.review.vo.in.AddReviewRequestVo;
import com.hummingbird.kr.starbuckslike.review.vo.out.ReviewCommentResponseVo;
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

import java.util.List;
import java.util.stream.Stream;

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
            @RequestBody AddReviewRequestVo vo
    ){
        reviewService.addReview(AddReviewRequestDto.of(vo));
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
    @Operation(summary = "리뷰 댓글 작성", description = "리뷰 댓글 작성 하기")
    @PostMapping("/comment")
    public CommonResponseEntity<Void> addReviewCommentV1(
            @RequestBody AddReviewCommentRequestVo vo
    ){
        reviewService.addReviewComment(AddReviewCommentRequestDto.of(vo));
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                null
        );
    }

    @Operation(summary = "리뷰 댓글 삭제", description = "리뷰 댓글 삭제하기")
    @PostMapping("/delete/comment")
    public CommonResponseEntity<Void> deleteReviewCommentV1(
            @RequestBody DeleteReviewCommentRequestDto requestDto
    ) {
        reviewService.deleteReviewComment(requestDto);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                null
        );
    }

    @Operation(summary = "리뷰 댓글 조회", description = "리뷰 id 로 리뷰 댓글 조회 ")
    @GetMapping("/list/comment/{reviewId}")
    public CommonResponseEntity<List<ReviewCommentResponseVo>> findReviewCommentByIdV1(
            @PathVariable("reviewId") Long reviewId
    ){
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                reviewService.findReviewCommentById(reviewId).stream().map(ReviewCommentResponseDto::toVo).toList()
        );
    }


}
