package com.hummingbird.kr.starbuckslike.product.dto.out;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hummingbird.kr.starbuckslike.product.dto.out.QProductOptionResponseDto is a Querydsl Projection type for ProductOptionResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QProductOptionResponseDto extends ConstructorExpression<ProductOptionResponseDto> {

    private static final long serialVersionUID = 236230483L;

    public QProductOptionResponseDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<Float> discountRate, com.querydsl.core.types.Expression<Boolean> isInputOption) {
        super(ProductOptionResponseDto.class, new Class<?>[]{long.class, String.class, int.class, float.class, boolean.class}, id, name, price, discountRate, isInputOption);
    }

}

