package com.hummingbird.kr.starbuckslike.product.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hummingbird.kr.starbuckslike.product.dto.QRequestProductInfoDto is a Querydsl Projection type for RequestProductInfoDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QRequestProductInfoDto extends ConstructorExpression<RequestProductInfoDto> {

    private static final long serialVersionUID = -546081480L;

    public QRequestProductInfoDto(com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<Boolean> isNew, com.querydsl.core.types.Expression<String> shortDescription, com.querydsl.core.types.Expression<Boolean> isDiscounted, com.querydsl.core.types.Expression<Float> discountRate) {
        super(RequestProductInfoDto.class, new Class<?>[]{String.class, boolean.class, String.class, boolean.class, float.class}, name, isNew, shortDescription, isDiscounted, discountRate);
    }

}

