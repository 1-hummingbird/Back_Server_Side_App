package com.hummingbird.kr.starbuckslike.cart.infrastructure.custom;

import com.hummingbird.kr.starbuckslike.cart.domain.Cart;

import java.util.List;

/**
 * custom 레포지토리는 조회 이외에 CREATE, UPDATE, DELETE 를 담당
 * @author 허정현
 */
public interface CustomCartRepository {

    // todo 장바구니 단건 선택 처리
    public void selectCartItem(Long cartId);

    // 장바구니 id 리스트로 장바구니 엔티티 조회
    public List<Cart> findCartItemsByCartIds(List<Long> cartIds);



    // 장바구니 단건 삭제
    public void removeCartItem(Long cartId);

    // 장바구니 전체 삭제
    public void removeAllCartItemsByMemberUID(String memberUID);

}
