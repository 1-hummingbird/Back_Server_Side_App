package com.hummingbird.kr.starbuckslike.product.vo.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductListResponseVo {
    private Long id; // 상품 id

    private String src;

    private String name; // 상품명

    private Integer price; // 가격

    private Boolean isNew; // 신규 등록 상품 여부

    private Boolean isDiscounted; // 할인 여부

    private Float discountRate; // 할인율

    private Boolean isAvailable; // 판매 여부

}
