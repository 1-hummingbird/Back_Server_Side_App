package com.hummingbird.kr.starbuckslike.cart.application;

import com.hummingbird.kr.starbuckslike.cart.dto.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CartService {

    /**
     * Create , Update, Delete
     */
    // 장바구니 아이템 추가 [상품디테일 페이지의 장바구니 추가버튼]
    @Transactional
    public void addCartItem(RequestAddCartItemDto requestAddCartItemDto);


    //  장바구니 아이템 (증가 또는 감소)
    @Transactional
    public void adjustCartItemQuantity(RequestAdjustCartItemDto requestAdjustCartItemDto);


    // 장바구니 단건 삭제 (soft)
    @Transactional
    public void removeCartItem(Long cartId);

    // 장바구니 전체 삭제 (soft)
    @Transactional
    public long removeAllCartItemsByUserUid(String userUid);

    // 장바구니 단건 선택
    @Transactional
    public void selectCartItem(Long cartId);

    // 장바구니 전체 선택(활성,비활성)
    public long selectCartItems(RequestSelectCartItemDto requestSelectCartItemDto);


    /**
     * Select
     */
    // 장바구니 ID 리스트 조회
    public List<Long> findAllCartIdByUserUid(String userUid);

    // 장바구니 옵션상품의 대표상품 이미지 조회
    ResponseCartItemImageDto findCartMainImageDtoById(Long cartId);

    // 장바구니 옵션상품 정보(옵션가격,수량,옵션명 등등) 조회
    ResponseCartItemDto findCartItemDtoById(Long cartId);



}