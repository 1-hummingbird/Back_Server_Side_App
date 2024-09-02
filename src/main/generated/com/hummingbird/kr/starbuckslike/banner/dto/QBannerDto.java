package com.hummingbird.kr.starbuckslike.banner.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hummingbird.kr.starbuckslike.banner.dto.QBannerDto is a Querydsl Projection type for BannerDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QBannerDto extends ConstructorExpression<BannerDto> {

    private static final long serialVersionUID = -2139881811L;

    public QBannerDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> image, com.querydsl.core.types.Expression<String> url) {
        super(BannerDto.class, new Class<?>[]{long.class, String.class, String.class}, id, image, url);
    }

}

