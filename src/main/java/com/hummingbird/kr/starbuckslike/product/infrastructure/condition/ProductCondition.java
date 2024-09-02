package com.hummingbird.kr.starbuckslike.product.infrastructure.condition;

import lombok.Data;

import java.util.List;

@Data
public class ProductCondition {

    private String path; // 카테고리 path
    private List<Long> childCategoryIds; // 카테고리 자식 Ids

    private PriceType priceType; // 상품 가격대


    private List<Long> exhibitionIds; // 기획전 Ids

    private OrderCondition orderCondition;  // 정렬 조건

}