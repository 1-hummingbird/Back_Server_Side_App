package com.hummingbird.kr.starbuckslike.cart.dto.in;

import com.hummingbird.kr.starbuckslike.cart.vo.RequestRemoveCartItemVo;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Getter
@Setter
@Builder
public class RequestRemoveCartItemDto {
    private Long cartId;
    private String memberUID;

    public static RequestRemoveCartItemDto toDto(RequestRemoveCartItemVo requestRemoveCartItemVo, String memberUID){
        return RequestRemoveCartItemDto.builder()
                .cartId(requestRemoveCartItemVo.getCartId())
                .memberUID(memberUID)
                .build();
    }

}
