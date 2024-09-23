package com.hummingbird.kr.starbuckslike.product.dto.out;

import com.hummingbird.kr.starbuckslike.product.vo.out.ProductListImageResponseVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 상품 리스트 이미지 DTO
 * @author 허정현
 */
@Data
@Builder
@NoArgsConstructor
public class ProductListImageResponseDto {
    private Long productId; // 상품 id
    private String src; // 상품 이미지 경로

    public ProductListImageResponseVo toVo(){
        return ProductListImageResponseVo
                .builder()
                .productId(productId)
                .src(src)
                .build();
    }

    @QueryProjection
    public ProductListImageResponseDto(Long productId, String src) {
        this.productId = productId;
        this.src = src;
    }
}
