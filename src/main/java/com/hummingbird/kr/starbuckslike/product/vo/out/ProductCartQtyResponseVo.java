package com.hummingbird.kr.starbuckslike.product.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductCartQtyResponseVo {
    private Long cartCount; // 해당 상품이 장바구니에 몇개 담겼는지
}
