package com.hummingbird.kr.starbuckslike.cart.infrastructure.search;

import com.hummingbird.kr.starbuckslike.cart.domain.Cart;
import com.hummingbird.kr.starbuckslike.cart.domain.QCart;
import com.hummingbird.kr.starbuckslike.cart.dto.out.QResponseCartItemDto;
import com.hummingbird.kr.starbuckslike.cart.dto.out.QResponseCartItemImageDto;
import com.hummingbird.kr.starbuckslike.cart.dto.out.ResponseCartItemDto;
import com.hummingbird.kr.starbuckslike.cart.dto.out.ResponseCartItemImageDto;
import com.hummingbird.kr.starbuckslike.product.domain.QProductImage;
import com.hummingbird.kr.starbuckslike.product.domain.QProductOption;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.hummingbird.kr.starbuckslike.cart.domain.QCart.cart;
import static com.hummingbird.kr.starbuckslike.product.domain.QProductImage.productImage;
import static com.hummingbird.kr.starbuckslike.product.domain.QProductOption.productOption;

@Repository
public class CartSearchImpl implements CartSearch {

    private final JPAQueryFactory queryFactory;
    public CartSearchImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Long> findAllCartIdByUserUid(String userUid) {
        return queryFactory
                    .select(cart.id)
                    .from(cart)
                    .where(cart.userUid.eq(userUid))
                    .fetch();
    }

    @Override
    public ResponseCartItemImageDto findCartMainImageDtoById(Long cartId) {
         return queryFactory
                .select(new QResponseCartItemImageDto(
                               Expressions.asNumber(cartId).as("cartId") // 조회컬럼 최소화
                             , productImage.imageUrl
                        )
                )
                .from(productImage)
                .join(cart).on(productImage.product.id.eq(cart.productId))
                .where(
                        productImage.seq.eq(0).and(cart.id.eq(cartId))
                )
                .fetchOne();
    }

    @Override
    public ResponseCartItemDto findCartItemDtoById(Long cartId) {
        return queryFactory
                .select(new QResponseCartItemDto(
                                Expressions.asNumber(cartId).as("cartId"),
                                cart.inputData, productOption.id, productOption.name,
                                cart.qty, productOption.price, productOption.discountRate
                        )
                )
                .from(productOption)
                .join(cart).on(productOption.id.eq(cart.productOption.id))
                .where(
                        cart.id.eq(cartId)
                )
                .fetchOne();
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
                        cart.userUid.eq(userUid)
                        .and(cart.productOption.id.eq(optionId))
                        .and(cart.isDeleted.eq(false))
                )
                .fetchOne();
    }

    @Override
    public Long findCartItemCountByMember(String userUid) {
        return queryFactory
                .select(cart.count())
                .from(cart)
                .where( cart.userUid.eq(userUid)
                        .and(cart.isDeleted.eq(false))
                )
                .fetchOne();
    }

}
