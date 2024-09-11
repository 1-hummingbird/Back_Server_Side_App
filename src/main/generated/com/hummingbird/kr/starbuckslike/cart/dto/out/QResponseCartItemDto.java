package com.hummingbird.kr.starbuckslike.cart.dto.out;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hummingbird.kr.starbuckslike.cart.dto.out.QResponseCartItemDto is a Querydsl Projection type for ResponseCartItemDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QResponseCartItemDto extends ConstructorExpression<ResponseCartItemDto> {

    private static final long serialVersionUID = 1960949689L;

    public QResponseCartItemDto(com.querydsl.core.types.Expression<Long> cartId, com.querydsl.core.types.Expression<String> inputData, com.querydsl.core.types.Expression<Long> optionId, com.querydsl.core.types.Expression<String> optionName, com.querydsl.core.types.Expression<Integer> cartQuantity, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<Float> discountRate) {
        super(ResponseCartItemDto.class, new Class<?>[]{long.class, String.class, long.class, String.class, int.class, int.class, float.class}, cartId, inputData, optionId, optionName, cartQuantity, price, discountRate);
    }

}

