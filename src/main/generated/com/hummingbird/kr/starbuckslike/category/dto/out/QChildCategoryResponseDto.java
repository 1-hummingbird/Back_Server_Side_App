package com.hummingbird.kr.starbuckslike.category.dto.out;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hummingbird.kr.starbuckslike.category.dto.out.QChildCategoryResponseDto is a Querydsl Projection type for ChildCategoryResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QChildCategoryResponseDto extends ConstructorExpression<ChildCategoryResponseDto> {

    private static final long serialVersionUID = -2040463560L;

    public QChildCategoryResponseDto(com.querydsl.core.types.Expression<String> categoryCode, com.querydsl.core.types.Expression<String> categoryName) {
        super(ChildCategoryResponseDto.class, new Class<?>[]{String.class, String.class}, categoryCode, categoryName);
    }

}

