package com.hummingbird.kr.starbuckslike.product.dto.out;

import com.hummingbird.kr.starbuckslike.product.vo.out.ProductListResponseVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 상품 리스트 DTO
 * @author 허정현
 */
@Getter
@NoArgsConstructor
public class ProductListResponseDto {
    private Long id; // 상품 id

    private String src; // 상품 이미지

    private String name; // 상품명

    private Integer price; // 가격

    private Boolean isNew; // 신규 등록 상품 여부

    private Boolean isDiscounted; // 할인 여부

    private Float discountRate; // 할인율

    private Boolean isAvailable; // 판매 여부

    @QueryProjection
    public ProductListResponseDto(Long id, String src, String name, Integer price, Boolean isNew,
                                  Boolean isDiscounted, Float discountRate, Boolean isAvailable) {
        this.id = id;
        this.src = src;
        this.name = name;
        this.price = price;
        this.isNew = isNew;
        this.isDiscounted = isDiscounted;
        this.discountRate = discountRate;
        this.isAvailable = isAvailable;
    }

    public ProductListResponseVo toVo(){
        return ProductListResponseVo.builder()
                .id(id)
                .src(src)
                .name(name)
                .price(price)
                .isNew(isNew)
                .isDiscounted(isDiscounted)
                .discountRate(discountRate)
                .isAvailable(isAvailable)
                .build();
    }
}
