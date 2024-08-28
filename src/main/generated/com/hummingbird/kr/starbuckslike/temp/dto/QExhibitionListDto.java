package com.hummingbird.kr.starbuckslike.temp.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hummingbird.kr.starbuckslike.temp.dto.QExhibitionListDto is a Querydsl Projection type for ExhibitionListDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QExhibitionListDto extends ConstructorExpression<ExhibitionListDto> {

    private static final long serialVersionUID = 252321428L;

    public QExhibitionListDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> name) {
        super(ExhibitionListDto.class, new Class<?>[]{long.class, String.class}, id, name);
    }

}

