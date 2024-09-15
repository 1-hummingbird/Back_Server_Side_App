package com.hummingbird.kr.starbuckslike.purchase.dto.out;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hummingbird.kr.starbuckslike.purchase.dto.out.QPurchaseDetailResponseDto is a Querydsl Projection type for PurchaseDetailResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QPurchaseDetailResponseDto extends ConstructorExpression<PurchaseDetailResponseDto> {

    private static final long serialVersionUID = 691820443L;

    public QPurchaseDetailResponseDto(com.querydsl.core.types.Expression<java.time.LocalDateTime> purchaseDate, com.querydsl.core.types.Expression<Long> totalPrice, com.querydsl.core.types.Expression<Long> totalDiscount, com.querydsl.core.types.Expression<String> userName, com.querydsl.core.types.Expression<String> address, com.querydsl.core.types.Expression<String> primaryPhone, com.querydsl.core.types.Expression<String> secondaryPhone, com.querydsl.core.types.Expression<String> memo) {
        super(PurchaseDetailResponseDto.class, new Class<?>[]{java.time.LocalDateTime.class, long.class, long.class, String.class, String.class, String.class, String.class, String.class}, purchaseDate, totalPrice, totalDiscount, userName, address, primaryPhone, secondaryPhone, memo);
    }

}

