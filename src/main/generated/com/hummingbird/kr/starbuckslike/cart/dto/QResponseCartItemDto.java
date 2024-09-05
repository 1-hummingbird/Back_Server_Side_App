package com.hummingbird.kr.starbuckslike.cart.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hummingbird.kr.starbuckslike.cart.dto.QResponseCartItemDto is a Querydsl Projection type for ResponseCartItemDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QResponseCartItemDto extends ConstructorExpression<ResponseCartItemDto> {

    private static final long serialVersionUID = -487137959L;

    public QResponseCartItemDto(com.querydsl.core.types.Expression<Long> cartId, com.querydsl.core.types.Expression<String> inputData, com.querydsl.core.types.Expression<Long> optionId, com.querydsl.core.types.Expression<String> optionName, com.querydsl.core.types.Expression<Long> quantity, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<com.hummingbird.kr.starbuckslike.product.domain.SalesStatus> status, com.querydsl.core.types.Expression<Float> discountRate) {
        super(ResponseCartItemDto.class, new Class<?>[]{long.class, String.class, long.class, String.class, long.class, int.class, com.hummingbird.kr.starbuckslike.product.domain.SalesStatus.class, float.class}, cartId, inputData, optionId, optionName, quantity, price, status, discountRate);
    }

}

