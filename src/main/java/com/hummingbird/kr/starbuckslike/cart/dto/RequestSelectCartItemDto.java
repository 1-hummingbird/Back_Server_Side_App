package com.hummingbird.kr.starbuckslike.cart.dto;

import com.hummingbird.kr.starbuckslike.cart.domain.CartStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 장바구니 선택  DTO
 * @author 허정현
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestSelectCartItemDto {

    // 선택된 장바구니 id 리스트
    private List<Long> cartIds = new ArrayList<>();

    // 전체 선택 활성화 or 비활성화
    private CartStatus cartStatus;



}
