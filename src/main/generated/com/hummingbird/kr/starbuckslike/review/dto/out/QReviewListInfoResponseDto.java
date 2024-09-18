package com.hummingbird.kr.starbuckslike.review.dto.out;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hummingbird.kr.starbuckslike.review.dto.out.QReviewListInfoResponseDto is a Querydsl Projection type for ReviewListInfoResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QReviewListInfoResponseDto extends ConstructorExpression<ReviewListInfoResponseDto> {

    private static final long serialVersionUID = -1958699392L;

    public QReviewListInfoResponseDto(com.querydsl.core.types.Expression<String> nickName, com.querydsl.core.types.Expression<Integer> star, com.querydsl.core.types.Expression<java.time.LocalDateTime> createAt, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<Integer> commentCount) {
        super(ReviewListInfoResponseDto.class, new Class<?>[]{String.class, int.class, java.time.LocalDateTime.class, String.class, int.class}, nickName, star, createAt, content, commentCount);
    }

}

