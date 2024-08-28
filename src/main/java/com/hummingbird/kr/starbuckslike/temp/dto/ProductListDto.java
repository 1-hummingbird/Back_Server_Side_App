package com.hummingbird.kr.starbuckslike.temp.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
//@AllArgsConstructor
@NoArgsConstructor
public class ProductListDto {
    private Long id;
    //private String src;

    private String name;

    private Integer price;

    @QueryProjection
    public ProductListDto(Long id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
