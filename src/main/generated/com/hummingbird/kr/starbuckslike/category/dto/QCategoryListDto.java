package com.hummingbird.kr.starbuckslike.category.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hummingbird.kr.starbuckslike.category.dto.QCategoryListDto is a Querydsl Projection type for CategoryListDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCategoryListDto extends ConstructorExpression<CategoryListDto> {

    private static final long serialVersionUID = 1515491247L;

    public QCategoryListDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> path) {
        super(CategoryListDto.class, new Class<?>[]{long.class, String.class, String.class}, id, name, path);
    }

}

