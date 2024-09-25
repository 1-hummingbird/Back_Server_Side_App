package com.hummingbird.kr.starbuckslike.cart.application;

import com.hummingbird.kr.starbuckslike.cart.domain.Cart;
import com.hummingbird.kr.starbuckslike.cart.domain.CartAdjustType;
import com.hummingbird.kr.starbuckslike.cart.dto.in.*;
import com.hummingbird.kr.starbuckslike.cart.dto.out.*;
import com.hummingbird.kr.starbuckslike.cart.infrastructure.CartRepository;
import com.hummingbird.kr.starbuckslike.cart.infrastructure.custom.CustomCartRepository;
import com.hummingbird.kr.starbuckslike.cart.infrastructure.search.CartSearch;
import com.hummingbird.kr.starbuckslike.product.domain.ProductOption;
import com.hummingbird.kr.starbuckslike.product.infrastructure.ProductOptionRepository;
import com.hummingbird.kr.starbuckslike.common.Exception.BaseException;
import com.hummingbird.kr.starbuckslike.common.entity.BaseResponseStatus;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
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
        canAddToCart(requestAddCartItemDto); // 장바구니에 넣을 수 있는지 체크
        // 이미 장바구니에 있는 상품인지 확인
        Long cartOptionCount =
                cartSearch.findCartOptionCount(requestAddCartItemDto.getMemberUID(), requestAddCartItemDto.getOptionId());
        // 처음 장바구니에 담는 상품
        if(cartOptionCount == 0L){
            ProductOption selectOption = productOptionRepository
                                            .findById(requestAddCartItemDto.getOptionId())
                                            .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_INTEREST));
            cartRepository.save(requestAddCartItemDto.toEntity(selectOption)); // 신규 장바구니 상품 저장 완료
        }
        // 이미 장바구니에 담긴 옵션, 수량만 더해주면 됨
        else if(cartOptionCount == 1L){
            Cart findCart =
                    cartSearch.findCartOption(requestAddCartItemDto.getMemberUID(), requestAddCartItemDto.getOptionId());
            findCart.changeQty(findCart.getQty() + requestAddCartItemDto.getQty()); // 수량 더해줌
            cartRepository.save(findCart);
        }
        else{
            // 같은 사람의 장바구니에 같은 상품이 2개가 담겼다. 문제있음 에러처리
            throw new BaseException(BaseResponseStatus.DISALLOWED_ACTION);
        }
    }

    @Override
    public void addCartItemV2(RequestAddCartItemDto requestAddCartItemDto) {
        canAddToCart(requestAddCartItemDto); // 장바구니에 넣을 수 있는지 체크
        // 이미 장바구니에 있는 상품인지 확인
        if(cartSearch.exists(requestAddCartItemDto.getMemberUID(), requestAddCartItemDto.getOptionId())){
            // 이미 존재
            Cart findCart =
                    cartSearch.findCartOption(requestAddCartItemDto.getMemberUID(), requestAddCartItemDto.getOptionId());
            findCart.changeQty(findCart.getQty() + requestAddCartItemDto.getQty()); // 수량 더해줌
            cartRepository.save(findCart);
        }
        else{
            // 처음 장바구니에 담는 상품
            ProductOption selectOption = productOptionRepository
                    .findById(requestAddCartItemDto.getOptionId())
                    .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_INTEREST));
            cartRepository.save(requestAddCartItemDto.toEntity(selectOption)); // 신규 장바구니 상품 저장 완료
        }
    }

    // 최대 장바구니 개수 제한 로직
    private void canAddToCart(RequestAddCartItemDto requestAddCartItemDto) {
        // 회원이 담을 수 있는 장바구니 아이템은 20개 까지다
        Long totalCount = cartSearch.findCartItemCountByMember(requestAddCartItemDto.getMemberUID());
        if(totalCount >= 20)
            throw new BaseException(BaseResponseStatus.DISALLOWED_ACTION);
        // 최소 수량 검증
        if(requestAddCartItemDto.getQty() < 1)
            throw new BaseException(BaseResponseStatus.DISALLOWED_ACTION);
    }


    @Override
    public void adjustCartItemQuantity(RequestAdjustCartItemDto requestAdjustCartItemDto) {
        Cart cart = cartRepository
                .findById(requestAdjustCartItemDto.getCartId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_INTEREST));
        // 장바구니 소유자 검증
        if(!cart.getMemberUID().equals(requestAdjustCartItemDto.getMemberUID())){
            throw new BaseException(BaseResponseStatus.DISALLOWED_ACTION);
        }
        int currentQty = cart.getQty();
        // 수량 조정 타입에 따른 처리
        if (requestAdjustCartItemDto.getCartAdjustType() == CartAdjustType.INCREASE) {
            cart.changeQty(currentQty + 1); // 수량 증가
        }
        else if (requestAdjustCartItemDto.getCartAdjustType() == CartAdjustType.DECREASE) {
            if (currentQty <= 1) {
                throw new BaseException(BaseResponseStatus.DISALLOWED_ACTION);
            }
            cart.changeQty(currentQty - 1); // 수량 감소
        } else {
            throw new BaseException(BaseResponseStatus.DISALLOWED_ACTION);
        }
        cartRepository.save(cart); // 변경된 상태 저장
    }

    @Override
    public void updateCartItemQuantity(RequestCartQtyDto dto, String memberUid) {
        // todo 유저가 남의 cartId 넣는 경우 보완해야 함
        Cart cart = cartRepository.findById(dto.getCartId()).orElseThrow();
        cartRepository.save(dto.toCart(cart));
    }

    @Override
    public void removeCartItem(RequestRemoveCartItemDto requestRemoveCartItemDto) {
        Cart cart = cartRepository
                .findById(requestRemoveCartItemDto.getCartId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_INTEREST));
        // 장바구니 소유자 검증
        if(!cart.getMemberUID().equals(requestRemoveCartItemDto.getMemberUID())){
            throw new BaseException(BaseResponseStatus.DISALLOWED_ACTION);
        }
        customCartRepository.removeCartItem(requestRemoveCartItemDto.getCartId());
    }

    @Override
    public void removeAllCartItemsByMemberUID(String memberUID) {
        customCartRepository.removeAllCartItemsByMemberUID(memberUID);
    }

    @Override
    public void selectCartItem(RequestSelectCartItemDto requestSelectCartItemDto) {
        Cart cart = cartRepository
                .findById(requestSelectCartItemDto.getCartId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_INTEREST));
        // 장바구니 소유자 검증
        if(!cart.getMemberUID().equals(requestSelectCartItemDto.getMemberUID())){
            throw new BaseException(BaseResponseStatus.DISALLOWED_ACTION);
        }
        customCartRepository.selectCartItem(requestSelectCartItemDto.getCartId());
    }


    @Override
    public void selectAllCartItems(RequestCartItemSelectAllDto requestCartItemSelectAllDto) {
        List<Cart> carts = customCartRepository.findCartItemsByCartIds(requestCartItemSelectAllDto.getCartIds());
        // todo 이것도 dto로 처리한다면 carts 가 아니라 DTO 리스트를 받은 다음 장바구니 객체를 생성해 바꿔주면 되는거죠??
        carts.forEach(Cart::toggleSelect); // 전체 선택 처리
        cartRepository.saveAll(carts);
    }


    @Override
    public ResponseFindAllCartDto findAllCartIdByMemberUID(String memberUID) {
        return new ResponseFindAllCartDto(cartSearch.findAllCartIdByMemberUID(memberUID));
    }

    @Override
    public ResponseCartItemImageDto findCartMainImageDtoById(Long cartId) {
        return cartSearch.findCartMainImageDtoById(cartId);
    }

    @Override
    public ResponseCartItemDto findCartItemDtoById(RequestCartInfoDto requestCartInfoDto) {
        return cartSearch.findCartItemDtoById(requestCartInfoDto.getCartId());
    }

}
