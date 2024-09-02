package com.hummingbird.kr.starbuckslike.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 상품 리스트 DTO
 * @author 허정현
 */
@Data
@Builder
//@AllArgsConstructor
@NoArgsConstructor
public class ProductListDto {
    private Long id; // 상품 id

    //todo : 상품 이미지경로 추가 예정
    //private String src;

    private String name; // 상품명

    private Integer price; // 가격

    private Boolean isNew; // 신규 등록 상품 여부

    private Boolean isDiscounted; // 할인 여부

    private Double discountRate; // 할인율



    @QueryProjection
    public ProductListDto(Long id, String name, Integer price, Boolean isNew,
                          Boolean isDiscounted, Double discountRate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isNew = isNew;
        this.isDiscounted = isDiscounted;
        this.discountRate = discountRate;
    }
}