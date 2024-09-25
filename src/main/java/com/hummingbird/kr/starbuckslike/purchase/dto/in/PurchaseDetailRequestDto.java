package com.hummingbird.kr.starbuckslike.purchase.dto.in;


import com.hummingbird.kr.starbuckslike.purchase.vo.in.PurchaseDetailRequestVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PurchaseDetailRequestDto {

    private String purchaseCode;

    public static PurchaseDetailRequestDto from(PurchaseDetailRequestVo purchaseDetailRequestVo) {
        return PurchaseDetailRequestDto.builder().purchaseCode(purchaseDetailRequestVo.getPurchaseCode()).build();
    }
}
