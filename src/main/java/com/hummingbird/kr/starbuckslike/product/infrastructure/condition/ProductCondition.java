package com.hummingbird.kr.starbuckslike.product.infrastructure.condition;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class ProductCondition {

    private String topCode; // 상 카테고리 코드
    private List<String> middleCode; // 카테고리 자식 code

    private String productName; // 상품명

    //@Parameter(description = "상품 가격대", schema = @Schema(implementation = PriceType.class))
    private PriceType priceType; // 상품 가격대

    private OrderCondition orderCondition;  // 정렬 조건

}