package com.hummingbird.kr.starbuckslike.exhibition.dto.out;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hummingbird.kr.starbuckslike.exhibition.dto.out.QExhibitionListResponseDto is a Querydsl Projection type for ExhibitionListResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QExhibitionListResponseDto extends ConstructorExpression<ExhibitionListResponseDto> {

    private static final long serialVersionUID = 91697390L;

    public QExhibitionListResponseDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> name) {
        super(ExhibitionListResponseDto.class, new Class<?>[]{long.class, String.class}, id, name);
    }

}

