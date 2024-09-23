package com.hummingbird.kr.starbuckslike.product.vo.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductListInfoResponseVo {
    private String name; // 상품명
    private Boolean isNew; // 신규 상품 여부
    private Boolean isDiscounted; // 할인 여부
    private Float discountRate; // 할인율
}
