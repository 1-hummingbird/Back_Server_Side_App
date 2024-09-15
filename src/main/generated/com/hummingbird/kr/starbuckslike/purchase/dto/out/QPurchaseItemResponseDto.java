package com.hummingbird.kr.starbuckslike.purchase.dto.out;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hummingbird.kr.starbuckslike.purchase.dto.out.QPurchaseItemResponseDto is a Querydsl Projection type for PurchaseItemResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QPurchaseItemResponseDto extends ConstructorExpression<PurchaseItemResponseDto> {

    private static final long serialVersionUID = 1175216921L;

    public QPurchaseItemResponseDto(com.querydsl.core.types.Expression<Long> purchaseId, com.querydsl.core.types.Expression<Long> optionId, com.querydsl.core.types.Expression<String> productImage, com.querydsl.core.types.Expression<String> optionName, com.querydsl.core.types.Expression<Long> price, com.querydsl.core.types.Expression<Integer> qty) {
        super(PurchaseItemResponseDto.class, new Class<?>[]{long.class, long.class, String.class, String.class, long.class, int.class}, purchaseId, optionId, productImage, optionName, price, qty);
    }

}

