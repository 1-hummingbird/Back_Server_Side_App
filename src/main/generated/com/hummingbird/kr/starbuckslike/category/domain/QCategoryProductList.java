package com.hummingbird.kr.starbuckslike.category.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCategoryProductList is a Querydsl query type for CategoryProductList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCategoryProductList extends EntityPathBase<CategoryProductList> {

    private static final long serialVersionUID = 867265866L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCategoryProductList categoryProductList = new QCategoryProductList("categoryProductList");

    public final com.hummingbird.kr.starbuckslike.common.entity.QBaseEntity _super = new com.hummingbird.kr.starbuckslike.common.entity.QBaseEntity(this);

    public final StringPath bottomCategoryCode = createString("bottomCategoryCode");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath middleCategoryCode = createString("middleCategoryCode");

    public final com.hummingbird.kr.starbuckslike.product.domain.QProduct product;

    public final StringPath topCategoryCode = createString("topCategoryCode");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QCategoryProductList(String variable) {
        this(CategoryProductList.class, forVariable(variable), INITS);
    }

    public QCategoryProductList(Path<? extends CategoryProductList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCategoryProductList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCategoryProductList(PathMetadata metadata, PathInits inits) {
        this(CategoryProductList.class, metadata, inits);
    }

    public QCategoryProductList(Class<? extends CategoryProductList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new com.hummingbird.kr.starbuckslike.product.domain.QProduct(forProperty("product")) : null;
    }

}

