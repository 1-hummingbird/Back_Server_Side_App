package com.hummingbird.kr.starbuckslike.cart.infrastructure.custom;

import com.hummingbird.kr.starbuckslike.cart.domain.CartStatus;
import com.hummingbird.kr.starbuckslike.cart.domain.QCart;
import com.hummingbird.kr.starbuckslike.cart.dto.RequestSelectCartItemDto;
import com.hummingbird.kr.starbuckslike.cart.infrastructure.CartRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.hummingbird.kr.starbuckslike.cart.domain.QCart.cart;

@Repository
@RequiredArgsConstructor
public class CustomCartRepositoryImpl implements  CustomCartRepository{
    private final JPAQueryFactory queryFactory;
    private final EntityManager em; // EntityManager 필드로 선언
    private final CartRepository cartRepository;

    @Override
    public void selectCartItem(Long cartId) {
        cartRepository.findById(cartId).orElseThrow().select();
    }

    @Override
    public void selectCartItems(RequestSelectCartItemDto requestSelectCartItemDto) {

        CartStatus status = requestSelectCartItemDto.getCartStatus();
        queryFactory
                .update(cart)
                .set(cart.isChecked, status == CartStatus.ACTIVE)
                .where(cart.userUid.eq(requestSelectCartItemDto.getUserUid()))  // 해당 유저의 장바구니
                .execute();
        em.clear();
    }

    @Override
    public void removeCartItem(Long cartId) {
        queryFactory
                .update(cart)
                .set(cart.isDeleted, true)
                .where(cart.id.eq(cartId))
                .execute();
    }

    @Override
    public void removeAllCartItemsByUserUid(String userUid) {
        long count = queryFactory
                .update(cart)
                .set(cart.isDeleted, true)
                .where(cart.userUid.eq(userUid))
                .execute();
        em.clear();
    }
}
