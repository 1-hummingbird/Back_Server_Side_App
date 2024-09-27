package com.hummingbird.kr.starbuckslike.product.dto.out;

import com.hummingbird.kr.starbuckslike.product.vo.out.ProductCartQtyResponseVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Query;

@Getter
@NoArgsConstructor
public class ProductCartQtyResponseDto {
    private Long cartCount; // 해당 상품이 장바구니에 몇개 담겼는지

    public ProductCartQtyResponseDto(Long cartCount) {
        this.cartCount = cartCount;
    }
    public ProductCartQtyResponseVo toVo(){
        return ProductCartQtyResponseVo.builder().cartCount(cartCount).build();
    }
}
