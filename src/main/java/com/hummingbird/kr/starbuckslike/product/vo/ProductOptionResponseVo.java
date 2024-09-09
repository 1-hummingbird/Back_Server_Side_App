package com.hummingbird.kr.starbuckslike.product.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductOptionResponseVo {
    private Long id; // 상품옵션 id
    private String name; // 상품 옵션명
    private Integer price; // 가격
    private Float discountRate; // 할인율
    private Boolean isInputOption; // 각인같은 사용자 입력 옵션 여부
}
