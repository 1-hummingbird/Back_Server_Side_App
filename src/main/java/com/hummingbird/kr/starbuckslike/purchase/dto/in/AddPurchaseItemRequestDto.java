package com.hummingbird.kr.starbuckslike.purchase.dto.in;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddPurchaseItemRequestDto {

    private Integer qty; // 선택 수량

    private Long price; // 가격 (상품옵션가격 * 수량)

    private Long discountPrice; // 해당 상품의 할인금액

    private String inputData; // 각인 같은 사용자 입력 데이터

    private Long productId;
    private String productName;

    private Long optionId;
    private String optionName;

}
