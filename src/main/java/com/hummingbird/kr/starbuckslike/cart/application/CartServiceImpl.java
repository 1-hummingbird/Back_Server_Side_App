package com.hummingbird.kr.starbuckslike.cart.application;

import com.hummingbird.kr.starbuckslike.cart.domain.Cart;
import com.hummingbird.kr.starbuckslike.cart.dto.AddCartItemDto;
import com.hummingbird.kr.starbuckslike.cart.infrastructure.CartRepository;
import com.hummingbird.kr.starbuckslike.cart.infrastructure.search.CartSearch;
import com.hummingbird.kr.starbuckslike.product.domain.ProductOption;
import com.hummingbird.kr.starbuckslike.product.infrastructure.ProductOptionRepository;
import com.hummingbird.kr.starbuckslike.product.infrastructure.ProductRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
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
    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;

    /**
     * 상품디테일에서 장바구니 추가 기능
     * 1. 회원이 담을 수 있는 장바구니 아이템은 20개 까지다 (선택 수량 아님)
     * 2. 최소 1개 이상의 수량을 선택해야 한다
     * 3. 아직 담지 않은 상품이면 신규 장바구니 아이템 생성, 아니라면 선택한수량을 기존 수량에 더해준다
     * 4. 신규 장바구니는 카운트 쿼리 결과 0 , 기존 장바구니는 1 로 판단한다. 그 외의 경우는 에러
     * @param addCartItemDto
     * @author 허정현
     */
    @Override
    public void addCartItem(AddCartItemDto addCartItemDto) {
        // 회원이 담을 수 있는 장바구니 아이템은 20개 까지다
        Long totalCount = cartSearch.findCartItemCountByMember(addCartItemDto.getMemberUID());
        if(totalCount >= 20)
            throw new IllegalStateException("장바구니에는 최대 20종류의 상품까지 담을 수 있습니다.");
        // 최소 수량 검증
        if(addCartItemDto.getQty() < 1)
            throw new IllegalStateException("장바구니에 담길 상품의 최소 수량은 1개 이상이어야 합니다.");
        // 이미 장바구니에 있는 상품인지 확인
        Long cartOptionCount =
                cartSearch.findCartOptionCount(addCartItemDto.getMemberUID(), addCartItemDto.getOptionId());
        // 처음 장바구니에 담는 상품
        if(cartOptionCount == 0L){
            ProductOption selectOption = productOptionRepository
                    .findById(addCartItemDto.getOptionId())
                    .orElseThrow(() -> new NoSuchElementException("상품 옵션을 찾을 수 없습니다. " ));
            // 장바구니 객체 생성
            Cart newCart = Cart.builder()
                    .productOption(selectOption)
                    .userUid(addCartItemDto.getMemberUID())
                    .qty(addCartItemDto.getQty())
                    .inputData(addCartItemDto.getInputData())
                    .build();
            cartRepository.save(newCart); // 신규 장바구니 상품 저장 완료
        }
        // 이미 장바구니에 담긴 옵션, 수량만 더해주면 됨
        else if(cartOptionCount == 1L){
            Cart findCart = cartSearch.findCartOption(addCartItemDto.getMemberUID(), addCartItemDto.getOptionId());
            findCart.addOptionQty(addCartItemDto.getQty()); // 수량 더해줌
        }
        else{
            // 같은 사람의 장바구니에 같은 상품이 2개가 담겼다. 문제있음 에러처리
            throw new IllegalStateException("동일한 상품이 장바구니에 담길 수 없습니다.");
        }
    }

    @Override
    public void removeCartItem(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    @Override
    public void removeCartItems(List<Long> cartIds, String userUid) {
        cartRepository.removeCartItems(cartIds,userUid);
    }

    @Override
    public void removeAllCartItem(String userUid) {
        cartRepository.removeAllCartItem(userUid);
    }

}
