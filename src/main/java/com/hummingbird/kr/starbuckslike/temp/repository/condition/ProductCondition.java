package com.hummingbird.kr.starbuckslike.temp.repository.condition;

import lombok.Data;

@Data
public class ProductCondition {

    private String path; // 카테고리 path

    private PriceType priceType; // 상품 가격대

    //이벤트
    private Long exhibitionId; // 기획전 Id

    // todo : 정렬 (최신순, 할인율, 베스트)
}