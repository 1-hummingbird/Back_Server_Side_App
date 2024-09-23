package com.hummingbird.kr.starbuckslike.cart.vo.out;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ResponseFindAllCartVo {
    private List<Long> cartIds;

}
