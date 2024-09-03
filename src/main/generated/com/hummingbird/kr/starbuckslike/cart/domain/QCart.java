package com.hummingbird.kr.starbuckslike.cart.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCart is a Querydsl query type for Cart
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCart extends EntityPathBase<Cart> {

    private static final long serialVersionUID = -1756974553L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCart cart = new QCart("cart");

    public final com.hummingbird.kr.starbuckslike.common.entity.QBaseEntity _super = new com.hummingbird.kr.starbuckslike.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath inputData = createString("inputData");

    public final com.hummingbird.kr.starbuckslike.product.domain.QProductOption productOption;

    public final NumberPath<Integer> qty = createNumber("qty", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath userUid = createString("userUid");

    public QCart(String variable) {
        this(Cart.class, forVariable(variable), INITS);
    }

    public QCart(Path<? extends Cart> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCart(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCart(PathMetadata metadata, PathInits inits) {
        this(Cart.class, metadata, inits);
    }

    public QCart(Class<? extends Cart> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.productOption = inits.isInitialized("productOption") ? new com.hummingbird.kr.starbuckslike.product.domain.QProductOption(forProperty("productOption"), inits.get("productOption")) : null;
    }

}

