package com.hummingbird.kr.starbuckslike.purchase.vo.out;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class PurchaseListResponseVo {
    private String purchaseCode; // 주문코드
    @JsonIgnore
    private Long purchaseId; // 구매 id
    private LocalDateTime purchaseDate; // 구매 날짜
    private Long totalPrice; // 총 주문 금액
    private List<PurchaseItemResponseVo> purchaseItems; // 주문 상품들
}
