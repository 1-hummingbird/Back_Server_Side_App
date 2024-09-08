package com.hummingbird.kr.starbuckslike.cart.infrastructure.custom;

import com.hummingbird.kr.starbuckslike.cart.domain.Cart;
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
        cartRepository.findById(cartId).orElseThrow().toggleSelect();
    }

    @Override
    public void selectAllCartItems(List<Long> cartIds) {
        List<Cart> carts = queryFactory
                .selectFrom(cart)
                .where(
                        cart.id.in(cartIds).and(cart.isDeleted.eq(false))
                )
                .fetch();
        carts.forEach(Cart::toggleSelect); //
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
