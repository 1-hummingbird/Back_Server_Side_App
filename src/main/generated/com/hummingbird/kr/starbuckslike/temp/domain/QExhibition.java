package com.hummingbird.kr.starbuckslike.temp.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QExhibition is a Querydsl query type for Exhibition
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExhibition extends EntityPathBase<Exhibition> {

    private static final long serialVersionUID = -1497354422L;

    public static final QExhibition exhibition = new QExhibition("exhibition");

    public final com.hummingbird.kr.starbuckslike.temp.domain.base.QBaseEntity _super = new com.hummingbird.kr.starbuckslike.temp.domain.base.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final StringPath fullDescription = createString("fullDescription");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isBanner = createBoolean("isBanner");

    public final StringPath name = createString("name");

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QExhibition(String variable) {
        super(Exhibition.class, forVariable(variable));
    }

    public QExhibition(Path<? extends Exhibition> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExhibition(PathMetadata metadata) {
        super(Exhibition.class, metadata);
    }

}

