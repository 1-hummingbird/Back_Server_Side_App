package com.hummingbird.kr.starbuckslike.product.infrastructure.condition;

import lombok.Data;

@Data
public class ProductCondition {

    private String path; // 카테고리 path

    private PriceType priceType; // 상품 가격대

    //이벤트
    private Long exhibitionId; // 기획전 Id

    private OrderCondition orderCondition;  // 정렬 조건

}