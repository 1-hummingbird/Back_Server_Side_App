package com.hummingbird.kr.starbuckslike.purchase.vo.out;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PurchaseItemResponseVo {
    @JsonIgnore
    private Long purchaseId; // 구매 id
    private Long optionId; // 상품 옵션 Id
    private String productImage; // 상품 대표이미지
    private String optionName;//상품옵션명
    private Long price; //주문 가격
    private Integer qty; //주문 수량

    private Boolean isReviewable; // 리뷰 작성가능 여부
}
