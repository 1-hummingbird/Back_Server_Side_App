package com.hummingbird.kr.starbuckslike.cart.infrastructure.search;

import com.hummingbird.kr.starbuckslike.cart.dto.CartListDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartSearch {

    // 회원 uuid로 회원 장바구니 리스트 조회
    List<CartListDto> findCartListDtoByUserUid(String userUid);

}
