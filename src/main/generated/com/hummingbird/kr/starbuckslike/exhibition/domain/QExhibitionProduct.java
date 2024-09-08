package com.hummingbird.kr.starbuckslike.exhibition.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExhibitionProduct is a Querydsl query type for ExhibitionProduct
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExhibitionProduct extends EntityPathBase<ExhibitionProduct> {

    private static final long serialVersionUID = -2030700310L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExhibitionProduct exhibitionProduct = new QExhibitionProduct("exhibitionProduct");

    public final QExhibition exhibition;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public QExhibitionProduct(String variable) {
        this(ExhibitionProduct.class, forVariable(variable), INITS);
    }

    public QExhibitionProduct(Path<? extends ExhibitionProduct> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExhibitionProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExhibitionProduct(PathMetadata metadata, PathInits inits) {
        this(ExhibitionProduct.class, metadata, inits);
    }

    public QExhibitionProduct(Class<? extends ExhibitionProduct> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.exhibition = inits.isInitialized("exhibition") ? new QExhibition(forProperty("exhibition")) : null;
    }

}

