package com.hummingbird.kr.starbuckslike.cart.dto.out;

import java.util.List;
import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class ResponseFindAllCartDto {
    private List<Long> cartIds;
}
