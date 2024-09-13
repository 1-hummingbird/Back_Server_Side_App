package com.hummingbird.kr.starbuckslike.product.dto.out;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hummingbird.kr.starbuckslike.product.dto.out.QProductListInfoResponseDto is a Querydsl Projection type for ProductListInfoResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QProductListInfoResponseDto extends ConstructorExpression<ProductListInfoResponseDto> {

    private static final long serialVersionUID = -1173855620L;

    public QProductListInfoResponseDto(com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<Boolean> isNew, com.querydsl.core.types.Expression<Boolean> isDiscounted, com.querydsl.core.types.Expression<Float> discountRate) {
        super(ProductListInfoResponseDto.class, new Class<?>[]{String.class, boolean.class, boolean.class, float.class}, name, isNew, isDiscounted, discountRate);
    }

}

