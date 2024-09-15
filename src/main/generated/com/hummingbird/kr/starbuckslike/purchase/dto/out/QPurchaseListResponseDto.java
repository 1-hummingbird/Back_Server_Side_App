package com.hummingbird.kr.starbuckslike.purchase.dto.out;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hummingbird.kr.starbuckslike.purchase.dto.out.QPurchaseListResponseDto is a Querydsl Projection type for PurchaseListResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QPurchaseListResponseDto extends ConstructorExpression<PurchaseListResponseDto> {

    private static final long serialVersionUID = -534980434L;

    public QPurchaseListResponseDto(com.querydsl.core.types.Expression<Long> purchaseId, com.querydsl.core.types.Expression<java.time.LocalDateTime> purchaseDate, com.querydsl.core.types.Expression<Long> totalPrice) {
        super(PurchaseListResponseDto.class, new Class<?>[]{long.class, java.time.LocalDateTime.class, long.class}, purchaseId, purchaseDate, totalPrice);
    }

    public QPurchaseListResponseDto(com.querydsl.core.types.Expression<Long> purchaseId, com.querydsl.core.types.Expression<java.time.LocalDateTime> purchaseDate, com.querydsl.core.types.Expression<Long> totalPrice, com.querydsl.core.types.Expression<? extends java.util.List<PurchaseItemResponseDto>> purchaseItems) {
        super(PurchaseListResponseDto.class, new Class<?>[]{long.class, java.time.LocalDateTime.class, long.class, java.util.List.class}, purchaseId, purchaseDate, totalPrice, purchaseItems);
    }

}

