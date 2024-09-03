package com.hummingbird.kr.starbuckslike.cart.application;

import com.hummingbird.kr.starbuckslike.cart.domain.Cart;
import com.hummingbird.kr.starbuckslike.cart.domain.CartAdjustType;
import com.hummingbird.kr.starbuckslike.cart.domain.CartStatus;
import com.hummingbird.kr.starbuckslike.cart.dto.*;
import com.hummingbird.kr.starbuckslike.cart.infrastructure.CartRepository;
import com.hummingbird.kr.starbuckslike.cart.infrastructure.custom.CustomCartRepository;
import com.hummingbird.kr.starbuckslike.cart.infrastructure.search.CartSearch;
import com.hummingbird.kr.starbuckslike.product.domain.ProductOption;
import com.hummingbird.kr.starbuckslike.product.infrastructure.ProductOptionRepository;
import com.hummingbird.kr.starbuckslike.product.infrastructure.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

    private final CartSearch cartSearch;
    private final CartRepository cartRepository;
    private final ProductOptionRepository productOptionRepository;
    private final CustomCartRepository customCartRepository;
    /**
     * 상품디테일에서 장바구니 추가 기능
     * 1. 회원이 담을 수 있는 장바구니 아이템은 20개 까지다 (선택 수량 아님)
     * 2. 최소 1개 이상의 수량을 선택해야 한다
     * 3. 아직 담지 않은 상품이면 신규 장바구니 아이템 생성, 아니라면 선택한수량을 기존 수량에 더해준다
     * 4. 신규 장바구니는 카운트 쿼리 결과 0 , 기존 장바구니는 1 로 판단한다. 그 외의 경우는 에러
     * @param requestAddCartItemDto
     * @author 허정현
     */
    @Override
    public void addCartItem(RequestAddCartItemDto requestAddCartItemDto) {
        // 회원이 담을 수 있는 장바구니 아이템은 20개 까지다
        Long totalCount = cartSearch.findCartItemCountByMember(requestAddCartItemDto.getMemberUID());
        if(totalCount >= 20)
            throw new IllegalStateException("장바구니에는 최대 20종류의 상품까지 담을 수 있습니다.");
        // 최소 수량 검증
        if(requestAddCartItemDto.getQty() < 1)
            throw new IllegalStateException("장바구니에 담길 상품의 최소 수량은 1개 이상이어야 합니다.");
        // 이미 장바구니에 있는 상품인지 확인
        Long cartOptionCount =
                cartSearch.findCartOptionCount(requestAddCartItemDto.getMemberUID(), requestAddCartItemDto.getOptionId());
        // 처음 장바구니에 담는 상품
        if(cartOptionCount == 0L){
            ProductOption selectOption = productOptionRepository
                                            .findById(requestAddCartItemDto.getOptionId())
                                            .orElseThrow(() -> new NoSuchElementException("상품 옵션을 찾을 수 없습니다. " ));
            // 장바구니 객체 생성
            Cart newCart = Cart.builder() // todo dto->entity 메서드 만들기
                    .product(selectOption.getProduct())
                    .productOption(selectOption)
                    .userUid(requestAddCartItemDto.getMemberUID())
                    .qty(requestAddCartItemDto.getQty())
                    .inputData(requestAddCartItemDto.getInputData())
                    .build();
            cartRepository.save(newCart); // 신규 장바구니 상품 저장 완료
        }
        // 이미 장바구니에 담긴 옵션, 수량만 더해주면 됨
        else if(cartOptionCount == 1L){
            Cart findCart = cartSearch.findCartOption(requestAddCartItemDto.getMemberUID(), requestAddCartItemDto.getOptionId());
            findCart.addOptionQty(requestAddCartItemDto.getQty()); // 수량 더해줌
        }
        else{
            // 같은 사람의 장바구니에 같은 상품이 2개가 담겼다. 문제있음 에러처리
            throw new IllegalStateException("동일한 상품이 장바구니에 담길 수 없습니다.");
        }
    }


    @Override
    public void adjustCartItemQuantity(RequestAdjustCartItemDto requestAdjustCartItemDto) {
        Cart cart = cartRepository
                .findById(requestAdjustCartItemDto.getCartId())
                .orElseThrow(() -> new NoSuchElementException("장바구니가 존재하지 않습니다."));
        // 타입에 따라 수량을 증가 또는 감소
        if (requestAdjustCartItemDto.getCartAdjustType() == CartAdjustType.INCREASE) {
            cart.increaseCartItemQuantity();
        } else if (requestAdjustCartItemDto.getCartAdjustType() == CartAdjustType.DECREASE) {
            cart.decreaseCartItemQuantity();
        } else {
            throw new IllegalArgumentException("잘못된 장바구니 수량 조정 타입입니다.");
        }
    }

    @Override
    public void removeCartItem(Long cartId) {
        customCartRepository.removeCartItem(cartId);
    }

    @Override
    public long removeAllCartItemsByUserUid(String userUid) {
        return customCartRepository.removeAllCartItemsByUserUid(userUid); // 업데이트 된 row 반환
    }

    @Override
    public void selectCartItem(Long cartId) {
        customCartRepository.selectCartItem(cartId);
    }

    @Override
    public long selectCartItems(RequestSelectCartItemDto requestSelectCartItemDto) {
        return customCartRepository.selectCartItems(requestSelectCartItemDto); // 업데이트 된 row 반환
    }


    @Override
    public List<Long> findAllCartIdByUserUid(String userUid) {
        return cartSearch.findAllCartIdByUserUid(userUid);
    }

    @Override
    public ResponseCartItemImageDto findCartMainImageDtoById(Long cartId) {
        return cartSearch.findCartMainImageDtoById(cartId);
    }

    @Override
    public ResponseCartItemDto findCartItemDtoById(Long cartId) {
        return cartSearch.findCartItemDtoById(cartId);
    }

}