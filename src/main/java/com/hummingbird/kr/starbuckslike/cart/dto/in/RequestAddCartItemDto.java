package com.hummingbird.kr.starbuckslike.cart.dto.in;

import com.hummingbird.kr.starbuckslike.cart.domain.Cart;
import com.hummingbird.kr.starbuckslike.cart.vo.RequestAddCartItemVo;
import com.hummingbird.kr.starbuckslike.product.domain.ProductOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 장바구니 아이템 DTO
 * 상품 디테일에서 장바구니 추가할때 받는 DTO
 * @author 허정현
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestAddCartItemDto {

    private String memberUID; // 유저 정보

    private Long productId; // 상품 id

    private Long optionId; // 상품 옵션 id

    private Integer qty; // 상품 옵션 선택 수량

    private String inputData; // 각인 옵션상품의 경우 입력 데이터

    public Cart toEntity(ProductOption productOption) {
        return Cart.builder()
                .userUid(memberUID)
                .productId(productId)
                .productOption(productOption)
                .qty(qty)
                .inputData(inputData)
                .isChecked(false)
                .isDeleted(false)
                .build();
    }

    public static RequestAddCartItemDto toDto (RequestAddCartItemVo requestAddCartItemVo){
        return  RequestAddCartItemDto.builder()
                .memberUID(requestAddCartItemVo.getMemberUID())
                .productId(requestAddCartItemVo.getProductId())
                .optionId(requestAddCartItemVo.getOptionId())
                .qty(requestAddCartItemVo.getQty())
                .inputData(requestAddCartItemVo.getInputData())
                .build();
    }


}
