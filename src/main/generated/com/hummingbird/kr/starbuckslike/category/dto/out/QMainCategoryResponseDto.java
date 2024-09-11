package com.hummingbird.kr.starbuckslike.category.dto.out;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hummingbird.kr.starbuckslike.category.dto.out.QMainCategoryResponseDto is a Querydsl Projection type for MainCategoryResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMainCategoryResponseDto extends ConstructorExpression<MainCategoryResponseDto> {

    private static final long serialVersionUID = 582054163L;

    public QMainCategoryResponseDto(com.querydsl.core.types.Expression<String> categoryCode, com.querydsl.core.types.Expression<String> imageUrl, com.querydsl.core.types.Expression<String> categoryName) {
        super(MainCategoryResponseDto.class, new Class<?>[]{String.class, String.class, String.class}, categoryCode, imageUrl, categoryName);
    }

}

