package com.hummingbird.kr.starbuckslike.cart.infrastructure.search;

import com.hummingbird.kr.starbuckslike.cart.domain.Cart;
import com.hummingbird.kr.starbuckslike.cart.domain.QCart;
import com.hummingbird.kr.starbuckslike.cart.dto.CartListDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.hummingbird.kr.starbuckslike.cart.domain.QCart.cart;

@Repository
public class CartSearchImpl implements CartSearch {

    private final JPAQueryFactory queryFactory;
    public CartSearchImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<CartListDto> findCartListDtoByUserUid(String userUid) {
        return null;
    }

    @Override
    public Cart findCartOption(String userUid, Long optionId) {
        return queryFactory
                .selectFrom(cart)
                .where(
                        cart.userUid.eq(userUid).and(cart.productOption.id.eq(optionId))
                )
                .fetchOne();
    }

    @Override
    public Long findCartOptionCount(String userUid, Long optionId) {
        return queryFactory
                .select(cart.count())
                .from(cart)
                .where(
                        cart.userUid.eq(userUid).and(cart.productOption.id.eq(optionId))
                )
                .fetchOne();
    }

    @Override
    public Long findCartItemCountByMember(String userUid) {
        return queryFactory
                .select(cart.count())
                .from(cart)
                .where( cart.userUid.eq(userUid) )
                .fetchOne();
    }

}
