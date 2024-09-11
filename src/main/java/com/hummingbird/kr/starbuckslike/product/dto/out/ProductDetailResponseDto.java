package com.hummingbird.kr.starbuckslike.product.dto.out;

import com.hummingbird.kr.starbuckslike.product.vo.ProductDetailResponseVo;
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
public class ProductDetailResponseDto {
    private String detail;

    @QueryProjection
    public ProductDetailResponseDto(String detail) {
        this.detail = detail;
    }

    public ProductDetailResponseVo toVo() {
        return ProductDetailResponseVo.builder()
                .detail(detail)
                .build();
    }
}
