package com.hummingbird.kr.starbuckslike.purchase.dto.out;

import com.hummingbird.kr.starbuckslike.purchase.vo.out.PurchaseItemResponseVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class PurchaseItemResponseDto {
    private Long purchaseId; // 구매 id
    private Long optionId; // 상품 옵션 Id
    private String productImage; // 상품 대표이미지
    private String optionName;//상품옵션명
    private Long price; //주문 가격
    private Integer qty; //주문 수량

    private Boolean isReviewable; // 리뷰 작성가능 여부

    @QueryProjection
    public PurchaseItemResponseDto(Long purchaseId, Long optionId, String productImage,
                                     String optionName, Long price, Integer qty, Boolean isReviewable) {
        this.purchaseId = purchaseId;
        this.optionId = optionId;
        this.productImage = productImage;
        this.optionName = optionName;
        this.price = price;
        this.qty = qty;
        this.isReviewable = isReviewable;
    }
    public PurchaseItemResponseVo toVo() {
        return PurchaseItemResponseVo.builder()
                .purchaseId(purchaseId)
                .optionId(optionId)
                .productImage(productImage)
                .optionName(optionName)
                .price(price)
                .qty(qty)
                .isReviewable(isReviewable)
                .build();
    }
}
