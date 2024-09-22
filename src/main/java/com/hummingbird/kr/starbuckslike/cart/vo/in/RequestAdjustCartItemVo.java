package com.hummingbird.kr.starbuckslike.cart.vo.in;

import com.hummingbird.kr.starbuckslike.cart.domain.CartAdjustType;
import lombok.Getter;

@Getter
public class RequestAdjustCartItemVo {
    private Long cartId;

    private CartAdjustType cartAdjustType;
}
