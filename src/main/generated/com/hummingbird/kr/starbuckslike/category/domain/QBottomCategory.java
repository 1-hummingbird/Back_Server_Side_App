package com.hummingbird.kr.starbuckslike.category.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBottomCategory is a Querydsl query type for BottomCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBottomCategory extends EntityPathBase<BottomCategory> {

    private static final long serialVersionUID = 1305158766L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBottomCategory bottomCategory = new QBottomCategory("bottomCategory");

    public final com.hummingbird.kr.starbuckslike.common.entity.QBaseEntity _super = new com.hummingbird.kr.starbuckslike.common.entity.QBaseEntity(this);

    public final StringPath categoryCode = createString("categoryCode");

    public final StringPath categoryDescription = createString("categoryDescription");

    public final StringPath categoryName = createString("categoryName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QMiddleCategory middleCategory;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QBottomCategory(String variable) {
        this(BottomCategory.class, forVariable(variable), INITS);
    }

    public QBottomCategory(Path<? extends BottomCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBottomCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBottomCategory(PathMetadata metadata, PathInits inits) {
        this(BottomCategory.class, metadata, inits);
    }

    public QBottomCategory(Class<? extends BottomCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.middleCategory = inits.isInitialized("middleCategory") ? new QMiddleCategory(forProperty("middleCategory"), inits.get("middleCategory")) : null;
    }

}

