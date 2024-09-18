package com.hummingbird.kr.starbuckslike.review.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReviewComment is a Querydsl query type for ReviewComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReviewComment extends EntityPathBase<ReviewComment> {

    private static final long serialVersionUID = 1769366088L;

    public static final QReviewComment reviewComment = new QReviewComment("reviewComment");

    public final com.hummingbird.kr.starbuckslike.common.entity.QBaseEntity _super = new com.hummingbird.kr.starbuckslike.common.entity.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath memberUID = createString("memberUID");

    public final StringPath nickname = createString("nickname");

    public final NumberPath<Long> reviewId = createNumber("reviewId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QReviewComment(String variable) {
        super(ReviewComment.class, forVariable(variable));
    }

    public QReviewComment(Path<? extends ReviewComment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReviewComment(PathMetadata metadata) {
        super(ReviewComment.class, metadata);
    }

}

