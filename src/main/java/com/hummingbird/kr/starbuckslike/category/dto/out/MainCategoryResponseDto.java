package com.hummingbird.kr.starbuckslike.category.dto.out;

import com.hummingbird.kr.starbuckslike.category.vo.MainCategoryResponseVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@ToString
@Getter
@Builder
@NoArgsConstructor
public class MainCategoryResponseDto {

    private String categoryCode;
    private String categoryName;
    private String imageUrl;

    public MainCategoryResponseVo toVo(){
        return MainCategoryResponseVo.builder()
                .categoryCode(categoryCode)
                .categoryName(categoryName)
                .imageUrl(imageUrl)
                .build();
    }

    @QueryProjection
    public MainCategoryResponseDto(String categoryCode,  String categoryName, String imageUrl) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.imageUrl = imageUrl;

    }
}
