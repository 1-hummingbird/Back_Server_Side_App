package com.hummingbird.kr.starbuckslike.review.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = 1370074135L;

    public static final QReview review = new QReview("review");

    public final com.hummingbird.kr.starbuckslike.common.entity.QBaseEntity _super = new com.hummingbird.kr.starbuckslike.common.entity.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDelete = createBoolean("isDelete");

    public final StringPath memberUID = createString("memberUID");

    public final StringPath nickname = createString("nickname");

    public final NumberPath<Long> optionId = createNumber("optionId", Long.class);

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final StringPath purchaseCode = createString("purchaseCode");

    public final NumberPath<Integer> star = createNumber("star", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QReview(String variable) {
        super(Review.class, forVariable(variable));
    }

    public QReview(Path<? extends Review> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReview(PathMetadata metadata) {
        super(Review.class, metadata);
    }

}

