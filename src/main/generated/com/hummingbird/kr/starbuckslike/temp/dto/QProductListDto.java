package com.hummingbird.kr.starbuckslike.temp.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hummingbird.kr.starbuckslike.temp.dto.QProductListDto is a Querydsl Projection type for ProductListDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QProductListDto extends ConstructorExpression<ProductListDto> {

    private static final long serialVersionUID = 1893485744L;

    public QProductListDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<Integer> price) {
        super(ProductListDto.class, new Class<?>[]{long.class, String.class, int.class}, id, name, price);
    }

}

