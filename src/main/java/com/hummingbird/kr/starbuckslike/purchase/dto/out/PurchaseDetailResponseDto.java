package com.hummingbird.kr.starbuckslike.purchase.dto.out;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PurchaseDetailResponseDto {
    @JsonIgnore
    private Long purchaseId; // 구매 id
    private Long optionId; // 상품 옵션 Id
    private String productImage; // 상품 대표이미지
    private String optionName;//상품옵션명
    private Long price; //주문 가격
    private Integer qty; //주문 수량

    @QueryProjection
    public PurchaseDetailResponseDto(Long purchaseId, Long optionId, String productImage,
                                     String optionName, Long price, Integer qty) {
        this.purchaseId = purchaseId;
        this.optionId = optionId;
        this.productImage = productImage;
        this.optionName = optionName;
        this.price = price;
        this.qty = qty;
    }
}
