package com.hummingbird.kr.starbuckslike.product.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
public class ProductImageResponseVo {
    private String url; // 상품 이미지 url
    private Boolean isMainImage; // 상품 대표이미지 여부

}
