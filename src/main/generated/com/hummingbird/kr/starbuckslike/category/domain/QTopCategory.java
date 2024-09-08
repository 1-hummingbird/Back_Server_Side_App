package com.hummingbird.kr.starbuckslike.category.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTopCategory is a Querydsl query type for TopCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTopCategory extends EntityPathBase<TopCategory> {

    private static final long serialVersionUID = -168496274L;

    public static final QTopCategory topCategory = new QTopCategory("topCategory");

    public final com.hummingbird.kr.starbuckslike.common.entity.QBaseEntity _super = new com.hummingbird.kr.starbuckslike.common.entity.QBaseEntity(this);

    public final StringPath categoryCode = createString("categoryCode");

    public final StringPath categoryDescription = createString("categoryDescription");

    public final StringPath categoryName = createString("categoryName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QTopCategory(String variable) {
        super(TopCategory.class, forVariable(variable));
    }

    public QTopCategory(Path<? extends TopCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTopCategory(PathMetadata metadata) {
        super(TopCategory.class, metadata);
    }

}

