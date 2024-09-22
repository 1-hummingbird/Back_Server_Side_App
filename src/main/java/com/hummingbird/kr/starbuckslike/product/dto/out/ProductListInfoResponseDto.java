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
    private Float discountRate; // 할인율

    public ProductListInfoResponseVo toVo(){
        return ProductListInfoResponseVo
                .builder()
                .name(name)
                .isNew(isNew)
                .isDiscounted(isDiscounted)
                .discountRate(discountRate)
                .build();
    }

    @QueryProjection
    public ProductListInfoResponseDto(String name, Boolean isNew, Boolean isDiscounted, Float discountRate) {
        this.name = name;
        this.isNew = isNew;
        this.isDiscounted = isDiscounted;
        this.discountRate = discountRate;
    }
}
