package com.hummingbird.kr.starbuckslike.product.dto.out;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hummingbird.kr.starbuckslike.product.dto.out.QProductInfoResponseDto is a Querydsl Projection type for ProductInfoResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QProductInfoResponseDto extends ConstructorExpression<ProductInfoResponseDto> {

    private static final long serialVersionUID = 186863610L;

    public QProductInfoResponseDto(com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<Boolean> isNew, com.querydsl.core.types.Expression<String> shortDescription, com.querydsl.core.types.Expression<Boolean> isDiscounted, com.querydsl.core.types.Expression<Float> discountRate) {
        super(ProductInfoResponseDto.class, new Class<?>[]{String.class, boolean.class, String.class, boolean.class, float.class}, name, isNew, shortDescription, isDiscounted, discountRate);
    }

}

