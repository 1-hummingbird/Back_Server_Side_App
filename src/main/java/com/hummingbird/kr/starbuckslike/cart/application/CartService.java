package com.hummingbird.kr.starbuckslike.cart.application;

import com.hummingbird.kr.starbuckslike.cart.dto.AddCartItemDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CartService {

    // 장바구니 아이템 추가 [상품디테일 페이지의 장바구니 추가버튼]
    @Transactional
    public void addCartItem(AddCartItemDto addCartItemDto);

    // todo 장바구니 옵션 증가,감소


    // 장바구니 단건 삭제
    @Transactional
    public void removeCartItem(Long cartId);

    // 장바구니 선택 삭제
    @Transactional
    public void removeCartItems(List<Long> cartIds, String userUid);

    // 장바구니 전체 삭제
    @Transactional
    public void removeAllCartItem(String userUid);



}
