package com.hummingbird.kr.starbuckslike.cart.vo.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCartItemImageVo
{
    // 장바구니 id
    private Long cartId;
    // 상품 이미지(대표 이미지 가져와야 함)
    private String productImg;
}
