package com.hummingbird.kr.starbuckslike.review.presentation;

import com.hummingbird.kr.starbuckslike.auth.domain.AuthUserDetail;
import com.hummingbird.kr.starbuckslike.common.entity.BaseResponse;
import com.hummingbird.kr.starbuckslike.common.entity.BaseResponseStatus;
import com.hummingbird.kr.starbuckslike.redis.facade.ReviewLockFacade;
import com.hummingbird.kr.starbuckslike.review.application.ReviewService;
import com.hummingbird.kr.starbuckslike.review.dto.in.*;
import com.hummingbird.kr.starbuckslike.review.dto.out.*;
import com.hummingbird.kr.starbuckslike.review.infrastructure.condition.ReviewCondition;
import com.hummingbird.kr.starbuckslike.review.vo.in.*;
import com.hummingbird.kr.starbuckslike.review.vo.out.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final ReviewLockFacade reviewLockFacade;

    @Operation(summary = "리뷰 리스트 조회 [페이징]", description = "상품 id 로 리뷰 리스트 조회 ", tags = {"리뷰"})
    @GetMapping("/list/{productId}")
    public BaseResponse<Slice<Long>> searchReviewListByIdV1(
            Pageable pageable, @PathVariable("productId") Long productId , ReviewCondition reviewCondition
    ){
        return new BaseResponse<>(
                reviewService.searchReviewListById(pageable, productId, reviewCondition)
        );
    }
    @Operation( security = @SecurityRequirement(name = "Bearer Auth"),
                summary = "회원이 쓴 리뷰 리스트 조회 [페이징]", description = "회원 UUID 로 리뷰 리스트 조회 ", tags = {"리뷰"})
    @PostMapping("/my/list")
    public BaseResponse<Slice<Long>> searchReviewListByMemberUuidV1(
            Pageable pageable, @AuthenticationPrincipal AuthUserDetail authUserDetail
    ){
        return new BaseResponse<>(
                reviewService.searchReviewListByMemberUuid(pageable, authUserDetail.getUsername())
        );
    }


    @Operation(summary = "리뷰 이미지 조회", description = "상품 id 로 리뷰 이미지 조회 ", tags = {"리뷰"})
    @GetMapping("/image/{productId}")
    public BaseResponse<ReviewListImageResponseVo> findReviewImageByIdV1(
            @PathVariable("productId") Long productId
    ){
        return new BaseResponse<>(
                reviewService.findReviewImageById(productId).toVo()
        );
    }

    @Operation(summary = "리뷰 내용 조회", description = "상품 id 로 리뷰 내용 조회 ", tags = {"리뷰"})
    @GetMapping("/info/{productId}")
    public BaseResponse<ReviewListInfoResponseVo> findReviewInfoByIdV1(
            @PathVariable("productId") Long productId
    ){
        return new BaseResponse<>(
                reviewService.findReviewInfoById(productId).toVo()
        );
    }

    @Operation( security = @SecurityRequirement(name = "Bearer Auth"),
                summary = "리뷰 작성", description = "리뷰 작성 하기", tags = {"리뷰"})
    @PostMapping("")
    public BaseResponse<Void> addReviewV1(
            @RequestBody AddReviewRequestVo vo, @AuthenticationPrincipal AuthUserDetail authUserDetail
    ){
        reviewService.addReview(AddReviewRequestDto.of(vo, authUserDetail.getUsername(), authUserDetail.getNickname()));
        return new BaseResponse<>(
              BaseResponseStatus.SUCCESS
        );
    }

    @Operation(summary = "리뷰 삭제" , description = "리뷰 삭제 하기(soft delete)", tags = {"리뷰"})
    @PostMapping("/delete/{reviewId}")
    public BaseResponse<Void> deleteReviewV1(@PathVariable("reviewId") Long reviewId, @AuthenticationPrincipal AuthUserDetail authUserDetail) {
        reviewService.deleteReview(reviewId);
        return new BaseResponse<>(
                BaseResponseStatus.SUCCESS
        );
    }
    @Operation(summary = "리뷰 댓글 작성", description = "리뷰 댓글 작성 하기", tags = {"리뷰"})
    @PostMapping("/comment")
    public BaseResponse<Void> addReviewCommentV1(
            @RequestBody AddReviewCommentRequestVo vo, @AuthenticationPrincipal AuthUserDetail authUserDetail
    ) throws InterruptedException {
        //reviewService.addReviewComment(AddReviewCommentRequestDto.of(vo));
        reviewLockFacade.addReviewCommentAndIncreaseCount(AddReviewCommentRequestDto.of(vo, authUserDetail.getUsername())); // 동시성 처리 완료
        return new BaseResponse<>(
                BaseResponseStatus.SUCCESS
        );
    }

    @Operation(summary = "리뷰 댓글 삭제", description = "리뷰 댓글 삭제하기", tags = {"리뷰"})
    @PostMapping("/delete/comment")
    public BaseResponse<Void> deleteReviewCommentV1(
            @RequestBody DeleteReviewCommentRequestVo requestVo, @AuthenticationPrincipal AuthUserDetail authUserDetail
    ) throws InterruptedException {
        //reviewService.deleteReviewComment(DeleteReviewCommentRequestDto.of(requestVo, authUserDetail.getUsername()));
        reviewLockFacade.deleteReviewAndDecreaseCount(DeleteReviewCommentRequestDto.of(requestVo, authUserDetail.getUsername())); // 동시성 처리 완료
        return new BaseResponse<>(
                BaseResponseStatus.SUCCESS
        );
    }

    @Operation(summary = "리뷰 댓글 조회", description = "리뷰 id 로 리뷰 댓글 조회 ", tags = {"리뷰"})
    @GetMapping("/list/comment/{reviewId}")
    public BaseResponse<List<ReviewCommentResponseVo>> findReviewCommentByIdV1(
            @PathVariable("reviewId") Long reviewId
    ){
        return new BaseResponse<>(
                reviewService.findReviewCommentById(reviewId).stream().map(ReviewCommentResponseDto::toVo).toList()
        );
    }


}
