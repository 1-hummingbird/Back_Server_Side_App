package com.hummingbird.kr.starbuckslike.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 상품 이미지 DTO
 * (상품 디테일 페이지에서 이미지 여러개일 수 있음)
 * @author 허정현
 */
@Data
@Builder
@NoArgsConstructor
public class ProductImageDto {

    private String url; // 상품 이미지 url

    private Boolean isMainImage; // 상품 대표이미지 여부

    @QueryProjection
    public ProductImageDto(String url, Boolean isMainImage) {
        this.url = url;
        this.isMainImage = isMainImage;
    }
}
