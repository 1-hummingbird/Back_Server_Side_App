package com.hummingbird.kr.starbuckslike.purchase.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPurchaseProduct is a Querydsl query type for PurchaseProduct
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPurchaseProduct extends EntityPathBase<PurchaseProduct> {

    private static final long serialVersionUID = -1435954746L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPurchaseProduct purchaseProduct = new QPurchaseProduct("purchaseProduct");

    public final com.hummingbird.kr.starbuckslike.common.entity.QBaseEntity _super = new com.hummingbird.kr.starbuckslike.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> discountPrice = createNumber("discountPrice", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath inputData = createString("inputData");

    public final NumberPath<Long> optionId = createNumber("optionId", Long.class);

    public final StringPath optionName = createString("optionName");

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final StringPath productName = createString("productName");

    public final QPurchase purchase;

    public final EnumPath<PurchaseStatus> purchaseStatus = createEnum("purchaseStatus", PurchaseStatus.class);

    public final NumberPath<Integer> qty = createNumber("qty", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QPurchaseProduct(String variable) {
        this(PurchaseProduct.class, forVariable(variable), INITS);
    }

    public QPurchaseProduct(Path<? extends PurchaseProduct> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPurchaseProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPurchaseProduct(PathMetadata metadata, PathInits inits) {
        this(PurchaseProduct.class, metadata, inits);
    }

    public QPurchaseProduct(Class<? extends PurchaseProduct> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.purchase = inits.isInitialized("purchase") ? new QPurchase(forProperty("purchase")) : null;
    }

}

