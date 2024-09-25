package com.hummingbird.kr.starbuckslike.cart.dto.in;

import com.hummingbird.kr.starbuckslike.cart.domain.Cart;
import com.hummingbird.kr.starbuckslike.cart.vo.in.RequestCartQtyVo;
import com.hummingbird.kr.starbuckslike.cart.vo.in.RequestRemoveCartItemVo;
import com.hummingbird.kr.starbuckslike.product.domain.ProductOption;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@AllArgsConstructor
@Builder
public class RequestCartQtyDto {
    private Long cartId;
    private Integer qty;

    public static RequestCartQtyDto toDto(RequestCartQtyVo vo){
        return RequestCartQtyDto.builder()
                .cartId(vo.getCartId())
                .qty(vo.getQty())
                .build();
    }

    public Cart toCart(Cart cart) {
        return Cart.builder()
                .id(cart.getId())
                .memberUID(cart.getMemberUID())
                .productId(cart.getProductId())
                .productOption(cart.getProductOption())
                .qty(qty)
                .inputData(cart.getInputData())
                .isChecked(cart.getIsChecked())
                .isDeleted(cart.getIsDeleted())
                .build();
    }

}
