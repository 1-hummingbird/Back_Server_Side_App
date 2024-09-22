package com.hummingbird.kr.starbuckslike.cart.dto.in;

import com.hummingbird.kr.starbuckslike.cart.domain.CartAdjustType;
import com.hummingbird.kr.starbuckslike.cart.vo.in.RequestAdjustCartItemVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 장바구니 수량 증가,감소  DTO
 * @author 허정현
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestAdjustCartItemDto {
    private Long cartId;

    private CartAdjustType cartAdjustType;

    private String memberUID;

    public static RequestAdjustCartItemDto toDto(RequestAdjustCartItemVo requestAdjustCartItemVo, String memberUID){
        return RequestAdjustCartItemDto.builder()
                .cartId(requestAdjustCartItemVo.getCartId())
                .cartAdjustType(requestAdjustCartItemVo.getCartAdjustType())
                .memberUID(memberUID)
                .build();
    }
}
