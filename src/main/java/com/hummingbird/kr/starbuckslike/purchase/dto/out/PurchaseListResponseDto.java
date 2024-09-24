package com.hummingbird.kr.starbuckslike.purchase.dto.out;

import com.hummingbird.kr.starbuckslike.purchase.vo.out.PurchaseItemResponseVo;
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
    private String purchaseCode; // 주문코드
    private Long purchaseId; // 구매 id
    private LocalDateTime purchaseDate; // 구매 날짜
    private Long totalPrice; // 총 주문 금액
    private List<PurchaseItemResponseDto> purchaseItems; // 주문 상품들

    @QueryProjection
    public PurchaseListResponseDto(String purchaseCode, Long purchaseId, LocalDateTime purchaseDate, Long totalPrice) {
        this.purchaseCode = purchaseCode;
        this.purchaseId = purchaseId;
        this.purchaseDate = purchaseDate;
        this.totalPrice = totalPrice;
    }

    @QueryProjection
    public PurchaseListResponseDto(String purchaseCode, Long purchaseId, LocalDateTime purchaseDate,
                                   Long totalPrice, List<PurchaseItemResponseDto> purchaseItems) {
        this.purchaseCode = purchaseCode;
        this.purchaseId = purchaseId;
        this.purchaseDate = purchaseDate;
        this.totalPrice = totalPrice;

        this.purchaseItems = purchaseItems;
    }

    public PurchaseListResponseVo toVo() {
        List<PurchaseItemResponseVo> purchaseItemVo = purchaseItems != null ?
                purchaseItems.stream().map(PurchaseItemResponseDto::toVo).toList() : new ArrayList<>();
        return PurchaseListResponseVo.builder()
                .purchaseCode(purchaseCode)
                .purchaseId(purchaseId)
                .purchaseDate(purchaseDate)
                .totalPrice(totalPrice)
                .purchaseItems(purchaseItemVo)
                .build();
    }
}
