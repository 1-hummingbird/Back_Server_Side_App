package com.hummingbird.kr.starbuckslike.cart.application;

import com.hummingbird.kr.starbuckslike.cart.dto.in.*;
import com.hummingbird.kr.starbuckslike.cart.dto.out.*;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CartService {
    /**
     * Create , Update, Delete
     */
    // 장바구니 아이템 추가 [상품디테일 페이지의 장바구니 추가버튼]
    @Transactional
    public void addCartItem(RequestAddCartItemDto requestAddCartItemDto);
    @Transactional
    public void addCartItemV2(RequestAddCartItemDto requestAddCartItemDto);

    //  장바구니 아이템 (증가 또는 감소)
    @Transactional
    public void adjustCartItemQuantity(RequestAdjustCartItemDto requestAdjustCartItemDto);

    // 개선 : 장바구니 수량 업데이트
    @Transactional
    public void updateCartItemQuantity(RequestCartQtyDto requestCartQtyDto, String memberUid);
    // 장바구니 단건 삭제 (soft)
    @Transactional
    public void removeCartItem(RequestRemoveCartItemDto requestRemoveCartItemDto);

    // 장바구니 전체 삭제 (soft)
    @Transactional
    public void removeAllCartItemsByMemberUID(String memberUID);

    // 장바구니 단건 선택
    @Transactional
    public void selectCartItem(RequestSelectCartItemDto requestSelectCartItemDto);

    // 장바구니 전체 선택
    @Transactional
    public void selectAllCartItems(RequestCartItemSelectAllDto requestCartItemSelectAllDto);

    /**
     * Select
     */
    // 장바구니 ID 리스트 조회
    ResponseFindAllCartDto findAllCartIdByMemberUID(String memberUID);

    // 장바구니 옵션상품의 대표상품 이미지 조회
    ResponseCartItemImageDto findCartMainImageDtoById(Long cartId);

    // 장바구니 옵션상품 정보(옵션가격,수량,옵션명 등등) 조회
    ResponseCartItemDto findCartItemDtoById(Long cartId);




}
