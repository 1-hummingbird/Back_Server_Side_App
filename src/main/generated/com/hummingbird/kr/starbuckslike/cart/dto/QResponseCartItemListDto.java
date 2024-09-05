package com.hummingbird.kr.starbuckslike.cart.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hummingbird.kr.starbuckslike.cart.dto.QResponseCartItemListDto is a Querydsl Projection type for ResponseCartItemListDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QResponseCartItemListDto extends ConstructorExpression<ResponseCartItemListDto> {

    private static final long serialVersionUID = 1006713563L;

    public QResponseCartItemListDto(com.querydsl.core.types.Expression<Long> cartId, com.querydsl.core.types.Expression<String> productImg, com.querydsl.core.types.Expression<Long> optionId, com.querydsl.core.types.Expression<String> optionName, com.querydsl.core.types.Expression<Integer> quantity, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<com.hummingbird.kr.starbuckslike.product.domain.SalesStatus> status, com.querydsl.core.types.Expression<Float> discountRate, com.querydsl.core.types.Expression<String> inputData) {
        super(ResponseCartItemListDto.class, new Class<?>[]{long.class, String.class, long.class, String.class, int.class, int.class, com.hummingbird.kr.starbuckslike.product.domain.SalesStatus.class, float.class, String.class}, cartId, productImg, optionId, optionName, quantity, price, status, discountRate, inputData);
    }

}

