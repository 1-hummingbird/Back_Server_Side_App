package com.hummingbird.kr.starbuckslike.product.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hummingbird.kr.starbuckslike.product.dto.QProductImageDto is a Querydsl Projection type for ProductImageDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QProductImageDto extends ConstructorExpression<ProductImageDto> {

    private static final long serialVersionUID = -217909190L;

    public QProductImageDto(com.querydsl.core.types.Expression<String> url, com.querydsl.core.types.Expression<Boolean> isMainImage) {
        super(ProductImageDto.class, new Class<?>[]{String.class, boolean.class}, url, isMainImage);
    }

}

