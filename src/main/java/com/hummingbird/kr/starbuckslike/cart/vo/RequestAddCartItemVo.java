package com.hummingbird.kr.starbuckslike.cart.vo;

import lombok.Getter;

@Getter
public class RequestAddCartItemVo {
    private String memberUID; // 유저 정보

    private Long productId; // 상품 id

    private Long optionId; // 상품 옵션 id

    private Integer qty; // 상품 옵션 선택 수량

    private String inputData; // 각인 옵션상품의 경우 입력 데이터
}
