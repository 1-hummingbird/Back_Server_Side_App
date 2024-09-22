package com.hummingbird.kr.starbuckslike.cart.dto.out;

import com.hummingbird.kr.starbuckslike.cart.vo.ResponseFindAllCartVo;

import java.util.List;
import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class ResponseFindAllCartDto {
    private List<Long> cartIds;

    public ResponseFindAllCartVo toVo(){
        return ResponseFindAllCartVo.builder()
                .cartIds(cartIds)
                .build();
    }
}
