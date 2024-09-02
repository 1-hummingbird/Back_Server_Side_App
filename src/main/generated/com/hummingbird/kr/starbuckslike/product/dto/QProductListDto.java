package com.hummingbird.kr.starbuckslike.product.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hummingbird.kr.starbuckslike.product.dto.QProductListDto is a Querydsl Projection type for ProductListDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QProductListDto extends ConstructorExpression<ProductListDto> {

    private static final long serialVersionUID = -351548437L;

    public QProductListDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<Boolean> isNew) {
        super(ProductListDto.class, new Class<?>[]{long.class, String.class, int.class, boolean.class}, id, name, price, isNew);
    }

}

