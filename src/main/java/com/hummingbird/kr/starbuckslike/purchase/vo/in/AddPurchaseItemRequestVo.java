package com.hummingbird.kr.starbuckslike.purchase.vo.in;

import lombok.Getter;

@Getter
public class AddPurchaseItemRequestVo {

    private Integer qty; // 선택 수량

    private Long price; // 가격 (상품옵션가격 * 수량)

    private Long discountPrice; // 해당 상품의 할인금액

    private String inputData; // 각인 같은 사용자 입력 데이터

    private Long productId;
    private String productName;

    private Long optionId;
    private String optionName;

}
