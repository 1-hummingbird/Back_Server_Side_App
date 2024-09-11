package com.hummingbird.kr.starbuckslike.product.dto.out;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hummingbird.kr.starbuckslike.product.dto.out.QProductListResponseDto is a Querydsl Projection type for ProductListResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QProductListResponseDto extends ConstructorExpression<ProductListResponseDto> {

    private static final long serialVersionUID = -226480630L;

    public QProductListResponseDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> src, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<Boolean> isNew, com.querydsl.core.types.Expression<Boolean> isDiscounted, com.querydsl.core.types.Expression<Float> discountRate, com.querydsl.core.types.Expression<Boolean> isAvailable) {
        super(ProductListResponseDto.class, new Class<?>[]{long.class, String.class, String.class, int.class, boolean.class, boolean.class, float.class, boolean.class}, id, src, name, price, isNew, isDiscounted, discountRate, isAvailable);
    }

}

