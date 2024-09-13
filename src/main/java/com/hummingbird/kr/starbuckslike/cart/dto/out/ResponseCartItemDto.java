package com.hummingbird.kr.starbuckslike.cart.dto.out;

import com.hummingbird.kr.starbuckslike.cart.vo.ResponseCartItemVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 장바구니 리스트 단건조회 API
 * 장바구니 상품옵션 정보 DTO
 * @author 허정현
 */
@Data
@Builder
@NoArgsConstructor
public class ResponseCartItemDto {
    // 장바구니 id
    private Long cartId;
    // 입력 데이터 (Cart 필드) , 입력데이터 없는 옵션상품이면 null 들어감. 추후 개선 필요
    private String inputData;

    // 상품 id (구매를 위해)
    private Long productId;
    // 상품명
    private String productName;

    // 상품 옵션 id (구매를 위해)
    private Long optionId;
    // 상품 옵션명
    private String optionName;
    // 장바구니 선택 수량
    private Integer cartQuantity;
    // 옵션 가격
    private Integer price;
    // 옵션 할인율
    private Float discountRate;

    @QueryProjection
    public ResponseCartItemDto(Long cartId, String inputData, Long productId, String productName, Long optionId,
                               String optionName, Integer cartQuantity, Integer price, Float discountRate) {
        this.cartId = cartId;
        this.inputData = inputData;
        this.productId = productId;
        this.productName = productName;
        this.optionId = optionId;
        this.optionName = optionName;
        this.cartQuantity = cartQuantity;
        this.price = price;
        this.discountRate = discountRate;
    }




    public ResponseCartItemVo toVo(){
        return ResponseCartItemVo.builder()
                .cartId(cartId)
                .inputData(inputData)
                .productId(productId)
                .productName(productName)
                .optionId(optionId)
                .optionName(optionName)
                .cartQuantity(cartQuantity)
                .price(price)
                .discountRate(discountRate)
                .build();
    }
}

