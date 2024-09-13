package com.hummingbird.kr.starbuckslike.product.dto.out;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hummingbird.kr.starbuckslike.product.dto.out.QProductImageResponseDto is a Querydsl Projection type for ProductImageResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QProductImageResponseDto extends ConstructorExpression<ProductImageResponseDto> {

    private static final long serialVersionUID = -1659895015L;

    public QProductImageResponseDto(com.querydsl.core.types.Expression<String> url, com.querydsl.core.types.Expression<Boolean> isMainImage) {
        super(ProductImageResponseDto.class, new Class<?>[]{String.class, boolean.class}, url, isMainImage);
    }

}

