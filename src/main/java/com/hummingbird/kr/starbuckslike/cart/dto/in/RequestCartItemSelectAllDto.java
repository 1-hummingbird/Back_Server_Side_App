package com.hummingbird.kr.starbuckslike.cart.dto.in;

import com.hummingbird.kr.starbuckslike.cart.vo.in.RequestCartItemSelectAllVo;

import java.util.List;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@AllArgsConstructor
@Builder
public class RequestCartItemSelectAllDto {
    private List<Long> cartIds;

    public static RequestCartItemSelectAllDto toDto(RequestCartItemSelectAllVo requestCartItemSelectAllVo) {
        return RequestCartItemSelectAllDto.builder()
                .cartIds(requestCartItemSelectAllVo.getCartIds())
                .build();
    }
}
