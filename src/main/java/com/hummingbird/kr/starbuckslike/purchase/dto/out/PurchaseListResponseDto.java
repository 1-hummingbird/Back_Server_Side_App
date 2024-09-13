package com.hummingbird.kr.starbuckslike.purchase.dto.out;

import com.hummingbird.kr.starbuckslike.purchase.domain.PurchaseStatus;
import com.hummingbird.kr.starbuckslike.purchase.vo.out.PurchaseDetailResponseVo;
import com.hummingbird.kr.starbuckslike.purchase.vo.out.PurchaseListResponseVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 주문 리스트  DTO
 * @author 허정현
 */
//@Data
@Getter
@Setter
@EqualsAndHashCode(of = "purchaseId")
public class PurchaseListResponseDto {
    private Long purchaseId; // 구매 id
    private LocalDateTime purchaseDate; // 구매 날짜
    private Long totalPrice; // 총 주문 금액
    private List<PurchaseDetailResponseDto> purchaseItems; // 주문 상품들

    @QueryProjection
    public PurchaseListResponseDto(Long purchaseId, LocalDateTime purchaseDate, Long totalPrice) {
        this.purchaseId = purchaseId;
        this.purchaseDate = purchaseDate;
        this.totalPrice = totalPrice;
    }

    @QueryProjection
    public PurchaseListResponseDto(Long purchaseId, LocalDateTime purchaseDate,
                                   Long totalPrice, List<PurchaseDetailResponseDto> purchaseItems) {
        this.purchaseId = purchaseId;
        this.purchaseDate = purchaseDate;
        this.totalPrice = totalPrice;
        this.purchaseItems = purchaseItems;
    }

    public PurchaseListResponseVo toVo() {
        List<PurchaseDetailResponseVo> purchaseItemVo = purchaseItems != null ?
                purchaseItems.stream().map(PurchaseDetailResponseDto::toVo).toList() : new ArrayList<>();
        return PurchaseListResponseVo.builder()
                .purchaseId(purchaseId)
                .purchaseDate(purchaseDate)
                .totalPrice(totalPrice)
                .purchaseItems(purchaseItemVo)
                .build();
    }
}
