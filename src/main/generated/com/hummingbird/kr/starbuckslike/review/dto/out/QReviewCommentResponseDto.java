package com.hummingbird.kr.starbuckslike.review.dto.out;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hummingbird.kr.starbuckslike.review.dto.out.QReviewCommentResponseDto is a Querydsl Projection type for ReviewCommentResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QReviewCommentResponseDto extends ConstructorExpression<ReviewCommentResponseDto> {

    private static final long serialVersionUID = -976397935L;

    public QReviewCommentResponseDto(com.querydsl.core.types.Expression<String> nickname, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<java.time.LocalDateTime> createAt) {
        super(ReviewCommentResponseDto.class, new Class<?>[]{String.class, String.class, java.time.LocalDateTime.class}, nickname, content, createAt);
    }

}

