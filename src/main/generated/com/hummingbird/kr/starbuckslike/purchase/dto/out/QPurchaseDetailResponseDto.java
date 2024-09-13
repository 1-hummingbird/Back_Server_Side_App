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

    public QPurchaseDetailResponseDto(com.querydsl.core.types.Expression<Long> purchaseId, com.querydsl.core.types.Expression<Long> optionId, com.querydsl.core.types.Expression<String> productImage, com.querydsl.core.types.Expression<String> optionName, com.querydsl.core.types.Expression<Long> price, com.querydsl.core.types.Expression<Integer> qty) {
        super(PurchaseDetailResponseDto.class, new Class<?>[]{long.class, long.class, String.class, String.class, long.class, int.class}, purchaseId, optionId, productImage, optionName, price, qty);
    }

}

