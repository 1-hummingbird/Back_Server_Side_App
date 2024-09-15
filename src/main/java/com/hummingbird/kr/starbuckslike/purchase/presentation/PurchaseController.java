package com.hummingbird.kr.starbuckslike.purchase.presentation;

import com.hummingbird.kr.starbuckslike.common.entity.CommonResponseEntity;
import com.hummingbird.kr.starbuckslike.common.entity.CommonResponseMessage;
import com.hummingbird.kr.starbuckslike.product.vo.ProductDetailResponseVo;
import com.hummingbird.kr.starbuckslike.purchase.application.PurchaseService;
import com.hummingbird.kr.starbuckslike.purchase.dto.out.PurchaseListResponseDto;
import com.hummingbird.kr.starbuckslike.purchase.vo.out.PurchaseListResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/purchase")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @Operation(summary = "회원 주문목록 조회 리스트 [페이징]", description = "회원 uuid로 주문목록 리스트 조회 ")
    @GetMapping("/list")
    public CommonResponseEntity<Slice<PurchaseListResponseVo>> searchPurchaseByUuidV1(
            Pageable pageable,
            @RequestParam("memberUuid") String memberUuid,
            @RequestParam(value = "year", required = false) Integer year
    ){
        Slice<PurchaseListResponseDto> purchaseListSlice =
                purchaseService.searchPurchaseByUuid(pageable, memberUuid, year);
        Slice<PurchaseListResponseVo> res = purchaseListSlice.map(PurchaseListResponseDto::toVo);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                res
        );
    }



}