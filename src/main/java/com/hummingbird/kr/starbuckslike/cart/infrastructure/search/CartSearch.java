package com.hummingbird.kr.starbuckslike.cart.infrastructure.search;

import com.hummingbird.kr.starbuckslike.cart.domain.Cart;
import com.hummingbird.kr.starbuckslike.cart.dto.CartListDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartSearch {


    // 회원 uuid로 회원 장바구니 리스트 조회
    List<CartListDto> findCartListDtoByUserUid(String userUid);

    // 회원 장바구니에 담은 상품 조회
    Cart findCartOption(String userUid, Long optionId);


    // 회원 장바구니에  상품옵션 담았는지 확인 [ 1 : 이미 존재 , 0 : 없음  ]
    Long findCartOptionCount(String userUid, Long optionId);

    // 회원의 장바구니에 삼은 상품들의 숫자 (20개로 제한)
    Long findCartItemCountByMember(String userUid);

}
