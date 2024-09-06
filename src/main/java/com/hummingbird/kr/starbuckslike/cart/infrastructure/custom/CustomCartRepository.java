package com.hummingbird.kr.starbuckslike.cart.infrastructure.custom;

import com.hummingbird.kr.starbuckslike.cart.dto.RequestSelectCartItemDto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * custom 레포지토리는 조회 이외에 CREATE, UPDATE, DELETE 를 담당
 * @author 허정현
 */
public interface CustomCartRepository {

    // todo 장바구니 단건 선택 처리
    public void selectCartItem(Long cartId);

    // 장바구니 전체 선택(활성,비활성)
    public void selectCartItems(RequestSelectCartItemDto requestSelectCartItemDto);



    // 장바구니 단건 삭제
    public void removeCartItem(Long cartId);

    // 장바구니 전체 삭제
    public void removeAllCartItemsByUserUid(String userUid);

}
