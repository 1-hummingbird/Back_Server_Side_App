package com.hummingbird.kr.starbuckslike.cart.infrastructure.search;

import com.hummingbird.kr.starbuckslike.cart.domain.Cart;
import com.hummingbird.kr.starbuckslike.cart.domain.QCart;
import com.hummingbird.kr.starbuckslike.cart.dto.out.QResponseCartItemDto;
import com.hummingbird.kr.starbuckslike.cart.dto.out.QResponseCartItemImageDto;
import com.hummingbird.kr.starbuckslike.cart.dto.out.ResponseCartItemDto;
import com.hummingbird.kr.starbuckslike.cart.dto.out.ResponseCartItemImageDto;

import com.hummingbird.kr.starbuckslike.product.domain.QProduct;
import com.hummingbird.kr.starbuckslike.product.domain.QProductImage;
import com.hummingbird.kr.starbuckslike.product.domain.QProductOption;
import com.querydsl.core.Tuple;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<Long> findAllCartIdByMemberUID(String membereUID) {
        List<Tuple> fetch = queryFactory
                .select(cart.id, cart.updatedAt)
                .from(cart)
                .where(
                        cart.memberUID.eq(membereUID)
                        .and(cart.isDeleted.isFalse())
                )
                //.orderBy(cart.updatedAt.desc())
                .fetch();
        return fetch.stream()
                // updatedAt  내림차순 정렬
                .sorted(Comparator.comparing(tuple -> tuple.get(cart.updatedAt), Comparator.reverseOrder()))
                .map(tuple -> tuple.get(cart.id)) // 장바구니 id 값만 추출
                .collect(Collectors.toList());
    }

    @Override
    public ResponseCartItemImageDto findCartMainImageDtoById(Long cartId) {
         return queryFactory
                .select(new QResponseCartItemImageDto(
                              Expressions.asNumber(cartId).as("cartId") ,// 조회컬럼 최소화
                              productImage.imageUrl
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
                                Expressions.asNumber(cartId).as("cartId"), cart.isChecked ,
                                cart.inputData, productOption.product.id, productOption.product.name,
                                productOption.id, productOption.name,
                                cart.qty, productOption.price, productOption.discountRate
                        )
                )
                .from(productOption)
                //.join(cart).on(productOption.id.eq(cart.productOption.id))
                .join(cart).on(productOption.id.eq(cart.productOptionId))
                .where(
                    cart.id.eq(cartId)
                )
                .fetchOne();
    }

    @Override
    public Cart findCartOption(String memberUID, Long optionId) {
        return queryFactory
                .selectFrom(cart)
                .where(
                        //cart.memberUID.eq(memberUID).and(cart.productOption.id.eq(optionId))
                        cart.memberUID.eq(memberUID).and(cart.productOptionId.eq(optionId))
                )
                .fetchOne();
    }

    @Override
    public Long findCartOptionCount(String memberUID, Long optionId) {
        return queryFactory
                .select(cart.count())
                .from(cart)
                .where(
                        cart.memberUID.eq(memberUID)
                        //.and(cart.productOption.id.eq(optionId))
                        .and(cart.productOptionId.eq(optionId))
                        .and(cart.isDeleted.eq(false))
                )
                .fetchOne();
    }

    @Override
    public Boolean exists(String memberUID, Long optionId) {
        Integer fetchOne = queryFactory
                .selectOne()
                .from(cart)
                .where(
                        cart.memberUID.eq(memberUID)
                        //.and(cart.productOption.id.eq(optionId))
                        .and(cart.productOptionId.eq(optionId))
                        .and(cart.isDeleted.isFalse())
                )
                .fetchFirst();

        return fetchOne != null;
    }

    @Override
    public Long findCartItemCountByMember(String memberUID) {
        return queryFactory
                .select(cart.count())
                .from(cart)
                .where( cart.memberUID.eq(memberUID)
                        .and(cart.isDeleted.eq(false))
                )
                .fetchOne();
    }

}
