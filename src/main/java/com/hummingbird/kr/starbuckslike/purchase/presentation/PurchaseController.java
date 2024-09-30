package com.hummingbird.kr.starbuckslike.purchase.presentation;

import com.hummingbird.kr.starbuckslike.common.entity.BaseResponse;
import com.hummingbird.kr.starbuckslike.common.entity.BaseResponseStatus;
import com.hummingbird.kr.starbuckslike.auth.domain.AuthUserDetail;
import com.hummingbird.kr.starbuckslike.purchase.application.PurchaseService;
import com.hummingbird.kr.starbuckslike.purchase.dto.in.AddPurchaseRequestDto;
import com.hummingbird.kr.starbuckslike.purchase.dto.in.DeletePurchaseRequestDto;
import com.hummingbird.kr.starbuckslike.purchase.dto.in.PurchaseDetailRequestDto;
import com.hummingbird.kr.starbuckslike.purchase.dto.out.PurchaseListResponseDto;
import com.hummingbird.kr.starbuckslike.purchase.vo.in.AddPurchaseRequestVo;
import com.hummingbird.kr.starbuckslike.purchase.vo.in.PurchaseDetailRequestVo;
import com.hummingbird.kr.starbuckslike.purchase.vo.out.PurchaseDetailResponseVo;
import com.hummingbird.kr.starbuckslike.purchase.vo.out.PurchaseListResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/purchase")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @Operation(summary = "회원 주문목록 조회 리스트 [페이징]", description = "회원 uuid로 주문목록 리스트 조회 ", tags = {"주문"}, security = @SecurityRequirement(name = "Bearer Auth"))
    @PostMapping("/list")
    public BaseResponse<Slice<PurchaseListResponseVo>> searchPurchaseByUuidV1(
            @AuthenticationPrincipal AuthUserDetail authUserDetail,
            @ParameterObject Pageable pageable,
            @RequestParam(value = "year", required = false) Integer year
    ){
        Slice<PurchaseListResponseDto> purchaseListSlice =
                purchaseService.searchPurchaseByUuid(pageable, authUserDetail.getUsername(), year);
        Slice<PurchaseListResponseVo> res = purchaseListSlice.map(PurchaseListResponseDto::toVo);
        return new BaseResponse<>(
                res
        );
    }

    @Operation(summary = "회원 주문생성", description = "주문 생성", security = @SecurityRequirement(name = "Bearer Auth"), tags = {"주문"})
    @PostMapping("")
    public BaseResponse<Void> addPurchaseV1(@AuthenticationPrincipal AuthUserDetail authUserDetail, @RequestBody AddPurchaseRequestVo vo){

        purchaseService.addPurchase(AddPurchaseRequestDto.of(vo, authUserDetail.getUsername()));
        return new BaseResponse<>(
                BaseResponseStatus.SUCCESS
        );
    }

    @Operation(summary = "회원 주문목록 디테일", description = "주문목록 디테일 조회", security = @SecurityRequirement(name = "Bearer Auth"), tags = {"주문"})
    @GetMapping("/detail")
    public BaseResponse<PurchaseDetailResponseVo> findPurchaseDetailByV1(
            @AuthenticationPrincipal AuthUserDetail authUserDetail,
            @RequestBody PurchaseDetailRequestVo purchaseDetailRequestVo
            ){
        return new BaseResponse<>(
                purchaseService.findPurchaseDetailById(PurchaseDetailRequestDto.from(purchaseDetailRequestVo)).toVo()
        );
    }
    @Operation(summary = "주문 삭제", description = "주문 삭제", security = @SecurityRequirement(name = "Bearer Auth"), tags = {"주문"})
    @DeleteMapping("")
    public BaseResponse<Void> deletePurchaseV1(
            @AuthenticationPrincipal AuthUserDetail authUserDetail,
            @RequestBody DeletePurchaseRequestDto dto) {
        purchaseService.deletePurchase(dto.getPurchaseCode());
        return new BaseResponse<>(
                BaseResponseStatus.SUCCESS
        );
    }

}
