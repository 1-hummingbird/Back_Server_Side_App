package com.hummingbird.kr.starbuckslike.cart.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hummingbird.kr.starbuckslike.cart.dto.QResponseCartItemImageDto is a Querydsl Projection type for ResponseCartItemImageDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QResponseCartItemImageDto extends ConstructorExpression<ResponseCartItemImageDto> {

    private static final long serialVersionUID = -1061460150L;

    public QResponseCartItemImageDto(com.querydsl.core.types.Expression<Long> cartId, com.querydsl.core.types.Expression<String> productImg) {
        super(ResponseCartItemImageDto.class, new Class<?>[]{long.class, String.class}, cartId, productImg);
    }

}

