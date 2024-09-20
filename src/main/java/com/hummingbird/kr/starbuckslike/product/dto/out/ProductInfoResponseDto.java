package com.hummingbird.kr.starbuckslike.product.dto.out;

import com.hummingbird.kr.starbuckslike.product.vo.out.ProductInfoResponseVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 상품 상세정보(상품명, 가격 등등) DTO
 * @author 허정현
 */
@Data
@NoArgsConstructor
public class ProductInfoResponseDto {
    private String name; // 상품명
    private Boolean isNew; // 신규 상품 여부
    private String shortDescription; // 짧은 상품설명 (텍스트)
    private Boolean isDiscounted; // 할인 여부
    private Float discountRate; // 할인율

    //
    private Long cartCount; // 해당 상품이 장바구니에 몇개 담겼는지

    @QueryProjection
    public ProductInfoResponseDto(String name, Boolean isNew, String shortDescription,
                                  Boolean isDiscounted, Float discountRate) {
        this.name = name;
        this.isNew = isNew;
        this.shortDescription = shortDescription;
        this.isDiscounted = isDiscounted;
        this.discountRate = discountRate;
    }
    @QueryProjection
    public ProductInfoResponseDto(String name, Boolean isNew, String shortDescription,
                                  Boolean isDiscounted, Float discountRate, Long cartCount) {
        this.name = name;
        this.isNew = isNew;
        this.shortDescription = shortDescription;
        this.isDiscounted = isDiscounted;
        this.discountRate = discountRate;
        this.cartCount = cartCount;
    }
    public ProductInfoResponseVo toVo(){
        return ProductInfoResponseVo.builder()
                .name(name)
                .isNew(isNew)
                .shortDescription(shortDescription)
                .isDiscounted(isDiscounted)
                .discountRate(discountRate)
                .cartCount(cartCount)
                .build();
    }


}
