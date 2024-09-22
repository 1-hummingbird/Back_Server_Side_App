package com.hummingbird.kr.starbuckslike.cart.infrastructure.search;

import com.hummingbird.kr.starbuckslike.cart.domain.Cart;
import com.hummingbird.kr.starbuckslike.cart.dto.out.ResponseCartItemDto;
import com.hummingbird.kr.starbuckslike.cart.dto.out.ResponseCartItemImageDto;

import java.util.List;

public interface CartSearch {

    // 회원 uuid로 회원 장바구니 번호 리스트 조회
    List<Long> findAllCartIdByMemberUID(String memberUID);

    // 장바구니 id로 장바구니 옵션상품의 대표이미지 조회
    ResponseCartItemImageDto findCartMainImageDtoById(Long cartId);

    // 장바구니 id로 장바구니 옵션상품 정보(옵션가격,수량,옵션명 등등) 조회
    ResponseCartItemDto findCartItemDtoById(Long cartId);

    // todo 각인옵션 같은 입력 정보 그냥 String으로 받는데 별도로 테이블로 빼는 것도 나중에 생각해보자
    // ex) 옵션타입T , 옵션타입디테일T  => 그래야 각인옵션에서도 한문 영어 등 자유롭게 선택가능하다

    // 회원 장바구니에 담은 상품 조회
    Cart findCartOption(String memberUID, Long optionId);

    // 회원 장바구니에  상품옵션 담았는지 확인 [ 1 : 이미 존재 , 0 : 없음  ]
    Long findCartOptionCount(String memberUID, Long optionId);

    Boolean exists(String userUid, Long optionId);

    // 회원의 장바구니에 삼은 상품들의 숫자 (20개로 제한)
    Long findCartItemCountByMember(String memberUID);

    //

}
