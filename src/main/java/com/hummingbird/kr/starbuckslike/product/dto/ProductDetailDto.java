package com.hummingbird.kr.starbuckslike.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 상품 상세정보 DTO
 * @author 허정현
 */
@Data
@Builder
@NoArgsConstructor
public class ProductDetailDto {
    private String detail;

    @QueryProjection
    public ProductDetailDto(String detail) {
        this.detail = detail;
    }
}
