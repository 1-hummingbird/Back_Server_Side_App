package com.hummingbird.kr.starbuckslike.product.infrastructure.condition;

import lombok.Data;

import java.util.List;

@Data
public class ProductCondition {

    private String topCode; // 상 카테고리 코드
    private List<String> middleCode; // 카테고리 자식 code

    private String productName; // 상품명
    private PriceType priceType; // 상품 가격대

    private OrderCondition orderCondition;  // 정렬 조건

}