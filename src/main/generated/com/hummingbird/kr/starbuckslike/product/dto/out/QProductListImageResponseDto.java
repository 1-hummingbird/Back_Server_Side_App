package com.hummingbird.kr.starbuckslike.product.dto.out;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hummingbird.kr.starbuckslike.product.dto.out.QProductListImageResponseDto is a Querydsl Projection type for ProductListImageResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QProductListImageResponseDto extends ConstructorExpression<ProductListImageResponseDto> {

    private static final long serialVersionUID = -892518185L;

    public QProductListImageResponseDto(com.querydsl.core.types.Expression<Long> productId, com.querydsl.core.types.Expression<String> src) {
        super(ProductListImageResponseDto.class, new Class<?>[]{long.class, String.class}, productId, src);
    }

}

