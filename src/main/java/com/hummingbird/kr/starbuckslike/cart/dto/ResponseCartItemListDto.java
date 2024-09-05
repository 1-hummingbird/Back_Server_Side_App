package com.hummingbird.kr.starbuckslike.cart.dto;

import com.hummingbird.kr.starbuckslike.product.domain.SalesStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 장바구니 리스트 DTO
 * @author 허정현
 */
@Data
@Builder
@NoArgsConstructor
public class ResponseCartItemListDto {
    // 장바구니 id
    private Long cartId;

    // 상품 이미지(대표 이미지 가져와야 함)
    private String productImg;

    // 상품 옵션 id (구매를 위해)
    private Long optionId;
    // 상품 옵션명
    private String optionName;
    // 수량
    private Integer quantity;
    // 옵션 가격
    private Integer price;
    // 판매상태(상품 옵션 판매상태)
    private SalesStatus status;
    // 옵션 할인율
    private Float discountRate;

    // 입력 데이터 (Cart 필드)
    private String inputData;

    @QueryProjection
    public ResponseCartItemListDto(Long cartId, String productImg, Long optionId,
                           String optionName, Integer quantity, Integer price, SalesStatus status,
                           Float discountRate, String inputData) {
        this.cartId = cartId;
        this.productImg = productImg;
        this.optionId = optionId;
        this.optionName = optionName;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.discountRate = discountRate;
        this.inputData = inputData;
    }
}

