package com.hummingbird.kr.starbuckslike.purchase.presentation;

import com.hummingbird.kr.starbuckslike.common.entity.CommonResponseEntity;
import com.hummingbird.kr.starbuckslike.common.entity.CommonResponseMessage;
import com.hummingbird.kr.starbuckslike.product.vo.ProductDetailResponseVo;
import com.hummingbird.kr.starbuckslike.purchase.application.PurchaseService;
import com.hummingbird.kr.starbuckslike.purchase.dto.out.PurchaseListResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/purchase")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @Operation(summary = "회원 주문목록 조회", description = "회원 uuid로 주문목록 리스트 조회")
    @GetMapping("/list/{memberUuid}")
    public CommonResponseEntity<List<PurchaseListResponseDto>> findPurchaseByUuidV1(
            @PathVariable("memberUuid") String memberUuid){
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                purchaseService.findPurchaseByUuid(memberUuid)
        );
    }
}
