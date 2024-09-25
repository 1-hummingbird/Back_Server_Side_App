package com.hummingbird.kr.starbuckslike.product.dto.out;

import com.hummingbird.kr.starbuckslike.product.vo.out.ProductIsWishedResponseVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class ProductIsWishedResponseDto {
    private Boolean isWished;

    @QueryProjection
    public ProductIsWishedResponseDto(Boolean isWished) {
        this.isWished = isWished;
    }

    public ProductIsWishedResponseVo toVo(){
        return ProductIsWishedResponseVo.builder().isWished(isWished).build();
    }

}
