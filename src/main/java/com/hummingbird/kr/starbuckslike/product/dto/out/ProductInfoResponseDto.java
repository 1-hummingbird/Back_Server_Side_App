package com.hummingbird.kr.starbuckslike.product.dto.out;

import com.hummingbird.kr.starbuckslike.product.vo.out.ProductInfoResponseVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 상품 상세정보(상품명, 가격 등등) DTO
 * @author 허정현
 */
@Getter
@NoArgsConstructor
public class ProductInfoResponseDto {
    private String name; // 상품명
    private Integer price; // 상품 가격
    private Boolean isNew; // 신규 상품 여부
    private String shortDescription; // 짧은 상품설명 (텍스트)
    private Boolean isDiscounted; // 할인 여부
    private Float discountRate; // 할인율


    @Setter
    private Long wishCount; // 해당 상품의 좋아요 개수


    @QueryProjection
    public ProductInfoResponseDto(String name, Integer price, Boolean isNew, String shortDescription,
                                  Boolean isDiscounted, Float discountRate) {
        this.name = name;
        this.price = price;
        this.isNew = isNew;
        this.shortDescription = shortDescription;
        this.isDiscounted = isDiscounted;
        this.discountRate = discountRate;
    }

    public ProductInfoResponseDto(String name, Integer price, Boolean isNew, String shortDescription,
                                  Boolean isDiscounted, Float discountRate, Long wishCount) {
        this.name = name;
        this.price = price;
        this.isNew = isNew;
        this.shortDescription = shortDescription;
        this.isDiscounted = isDiscounted;
        this.discountRate = discountRate;
        this.wishCount = wishCount;
    }

    public ProductInfoResponseVo toVo(){
        return ProductInfoResponseVo.builder()
                .name(name)
                .price(price)
                .isNew(isNew)
                .shortDescription(shortDescription)
                .isDiscounted(isDiscounted)
                .discountRate(discountRate)
                .wishCount(wishCount)
                .build();
    }


}
