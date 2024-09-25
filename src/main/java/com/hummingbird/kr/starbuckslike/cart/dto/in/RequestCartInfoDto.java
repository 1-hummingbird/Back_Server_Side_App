package com.hummingbird.kr.starbuckslike.cart.dto.in;

import com.hummingbird.kr.starbuckslike.cart.vo.in.RequestCartInfoVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RequestCartInfoDto {

    private Long cartId;

    public static RequestCartInfoDto from(RequestCartInfoVo vo){
        return RequestCartInfoDto.builder().cartId(vo.getCartId()).build();
    }

}
