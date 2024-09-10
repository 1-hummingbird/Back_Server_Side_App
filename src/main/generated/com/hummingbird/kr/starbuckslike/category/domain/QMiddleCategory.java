package com.hummingbird.kr.starbuckslike.category.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMiddleCategory is a Querydsl query type for MiddleCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMiddleCategory extends EntityPathBase<MiddleCategory> {

    private static final long serialVersionUID = 584800728L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMiddleCategory middleCategory = new QMiddleCategory("middleCategory");

    public final com.hummingbird.kr.starbuckslike.common.entity.QBaseEntity _super = new com.hummingbird.kr.starbuckslike.common.entity.QBaseEntity(this);

    public final StringPath categoryCode = createString("categoryCode");

    public final StringPath categoryDescription = createString("categoryDescription");

    public final StringPath categoryName = createString("categoryName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QTopCategory topCategory;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMiddleCategory(String variable) {
        this(MiddleCategory.class, forVariable(variable), INITS);
    }

    public QMiddleCategory(Path<? extends MiddleCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMiddleCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMiddleCategory(PathMetadata metadata, PathInits inits) {
        this(MiddleCategory.class, metadata, inits);
    }

    public QMiddleCategory(Class<? extends MiddleCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.topCategory = inits.isInitialized("topCategory") ? new QTopCategory(forProperty("topCategory")) : null;
    }

}

