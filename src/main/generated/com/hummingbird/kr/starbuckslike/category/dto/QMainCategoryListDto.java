package com.hummingbird.kr.starbuckslike.category.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hummingbird.kr.starbuckslike.category.dto.QMainCategoryListDto is a Querydsl Projection type for MainCategoryListDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMainCategoryListDto extends ConstructorExpression<MainCategoryListDto> {

    private static final long serialVersionUID = 881853110L;

    public QMainCategoryListDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> path, com.querydsl.core.types.Expression<String> image) {
        super(MainCategoryListDto.class, new Class<?>[]{long.class, String.class, String.class, String.class}, id, name, path, image);
    }

}

