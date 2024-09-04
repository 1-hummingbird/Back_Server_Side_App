package com.hummingbird.kr.starbuckslike.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 장바구니 아이템 DTO
 * 상품 디테일에서 장바구니 추가할때 받는 DTO
 * @author 허정현
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddCartItemDto {

    private String memberUID; // 유저 정보

    private Long optionId; // 상품 옵션 id

    private Integer qty = 1; // 상품 옵션 선택 수량

    private String inputData; // 각인 옵션상품의 경우 입력 데이터


}
