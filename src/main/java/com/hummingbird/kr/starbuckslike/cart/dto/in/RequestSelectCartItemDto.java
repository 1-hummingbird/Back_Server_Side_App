package com.hummingbird.kr.starbuckslike.cart.dto.in;

import com.hummingbird.kr.starbuckslike.cart.vo.RequestSelectCartItemVo;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@AllArgsConstructor
@Builder
public class RequestSelectCartItemDto {
    private Long cartId;
    private String memberUID;

    public static RequestSelectCartItemDto toDto(RequestSelectCartItemVo requestSelectCartItemVo, String memberUID) {
        return RequestSelectCartItemDto.builder()
                .cartId(requestSelectCartItemVo.getCartId())
                .memberUID(memberUID)
                .build();
    }
}
