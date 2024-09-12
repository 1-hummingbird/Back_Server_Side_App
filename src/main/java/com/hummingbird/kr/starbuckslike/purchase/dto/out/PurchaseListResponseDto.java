package com.hummingbird.kr.starbuckslike.purchase.dto.out;

import com.hummingbird.kr.starbuckslike.purchase.domain.PurchaseStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 주문 리스트  DTO
 * @author 허정현
 */
@Data
@EqualsAndHashCode(of = "purchaseId")
public class PurchaseListResponseDto {
    private Long purchaseId; // 구매 id
    private LocalDateTime purchaseDate; // 구매 날짜
    //private PurchaseStatus purchaseStatus; // 상태
    //private String address; // 배송 주소
    private Long totalPrice; // 총 주문 금액
    private List<PurchaseDetailResponseDto> purchaseItems; // 주문 상품들

    @Builder
    @QueryProjection
    public PurchaseListResponseDto(Long purchaseId, LocalDateTime purchaseDate, Long totalPrice) {
        this.purchaseId = purchaseId;
        this.purchaseDate = purchaseDate;
        this.totalPrice = totalPrice;
    }
    @Builder
    @QueryProjection
    public PurchaseListResponseDto(Long purchaseId, LocalDateTime purchaseDate,
                                   Long totalPrice, List<PurchaseDetailResponseDto> purchaseItems) {
        this.purchaseId = purchaseId;
        this.purchaseDate = purchaseDate;
        this.totalPrice = totalPrice;
        this.purchaseItems = purchaseItems;
    }
}
