package com.hummingbird.kr.starbuckslike.cart.dto;

import com.hummingbird.kr.starbuckslike.product.domain.SalesStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 장바구니 리스트 단건조회 API
 * 장바구니 상품 이미지 DTO
 * @author 허정현
 */
@Data
@Builder
@NoArgsConstructor
public class ResponseCartItemImageDto {
    // 장바구니 id
    private Long cartId;
    // 상품 이미지(대표 이미지 가져와야 함)
    private String productImg;

    @QueryProjection
    public ResponseCartItemImageDto(Long cartId, String productImg) {
        this.cartId = cartId;
        this.productImg = productImg;
    }
}

