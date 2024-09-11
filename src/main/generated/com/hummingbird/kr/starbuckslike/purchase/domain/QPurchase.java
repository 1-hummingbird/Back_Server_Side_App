package com.hummingbird.kr.starbuckslike.purchase.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPurchase is a Querydsl query type for Purchase
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPurchase extends EntityPathBase<Purchase> {

    private static final long serialVersionUID = -34346263L;

    public static final QPurchase purchase = new QPurchase("purchase");

    public final com.hummingbird.kr.starbuckslike.common.entity.QBaseEntity _super = new com.hummingbird.kr.starbuckslike.common.entity.QBaseEntity(this);

    public final StringPath address = createString("address");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath memo = createString("memo");

    public final StringPath primaryPhone = createString("primaryPhone");

    public final StringPath secondaryPhone = createString("secondaryPhone");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath userName = createString("userName");

    public final StringPath userUuid = createString("userUuid");

    public QPurchase(String variable) {
        super(Purchase.class, forVariable(variable));
    }

    public QPurchase(Path<? extends Purchase> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPurchase(PathMetadata metadata) {
        super(Purchase.class, metadata);
    }

}

