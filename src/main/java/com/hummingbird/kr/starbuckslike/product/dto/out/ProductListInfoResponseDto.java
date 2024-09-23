package com.hummingbird.kr.starbuckslike.product.dto.out;

import com.hummingbird.kr.starbuckslike.product.vo.out.ProductListInfoResponseVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 상품 리스트 정보 DTO
 * @author 허정현
 */
@Data
@Builder
@NoArgsConstructor
public class ProductListInfoResponseDto {

    private String name; // 상품명
    private Boolean isNew; // 신규 상품 여부
    private Boolean isDiscounted; // 할인 여부
    private Integer price; // 상품 가격
    private Float discountRate; // 할인율

    private Long wishCount; // 상품 별 좋아요 개수

    public ProductListInfoResponseVo toVo(){
        return ProductListInfoResponseVo
                .builder()
                .name(name)
                .isNew(isNew)
                .isDiscounted(isDiscounted)
                .price(price)
                .discountRate(discountRate)
                .wishCount(wishCount)
                .build();
    }

    @QueryProjection
    public ProductListInfoResponseDto(String name, Boolean isNew, Boolean isDiscounted,
                                      Integer price, Float discountRate) {
        this.name = name;
        this.isNew = isNew;
        this.isDiscounted = isDiscounted;
        this.price = price;
        this.discountRate = discountRate;
    }
    @QueryProjection
    public ProductListInfoResponseDto(String name, Boolean isNew, Boolean isDiscounted,
                                      Integer price, Float discountRate, Long wishCount) {
        this.name = name;
        this.isNew = isNew;
        this.isDiscounted = isDiscounted;
        this.price = price;
        this.discountRate = discountRate;
        this.wishCount = wishCount;
    }
}
