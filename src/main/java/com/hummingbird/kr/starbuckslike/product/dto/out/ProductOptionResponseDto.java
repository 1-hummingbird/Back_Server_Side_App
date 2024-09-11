package com.hummingbird.kr.starbuckslike.product.dto.out;

import com.hummingbird.kr.starbuckslike.product.vo.ProductOptionResponseVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class ProductOptionResponseDto {
    private Long id; // 상품옵션 id
    private String name; // 상품 옵션명
    private Integer price; // 가격
    private Float discountRate; // 할인율
    private Boolean isInputOption; // 각인같은 사용자 입력 옵션 여부

    @QueryProjection
    public ProductOptionResponseDto(Long id, String name, Integer price,
                                    Float discountRate, Boolean isInputOption) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discountRate = discountRate;
        this.isInputOption = isInputOption;
    }
    public ProductOptionResponseVo toVo(){
        return ProductOptionResponseVo.builder()
                .id(id)
                .name(name)
                .price(price)
                .discountRate(discountRate)
                .isInputOption(isInputOption)
                .build();
    }
}
