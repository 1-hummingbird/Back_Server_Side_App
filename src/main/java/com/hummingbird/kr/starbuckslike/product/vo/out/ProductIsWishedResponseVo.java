package com.hummingbird.kr.starbuckslike.product.vo.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductIsWishedResponseVo {
    private Boolean isWished;
}
