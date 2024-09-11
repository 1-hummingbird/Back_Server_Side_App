package com.hummingbird.kr.starbuckslike.category.dto.out;

import com.hummingbird.kr.starbuckslike.category.vo.ChildCategoryResponseVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class ChildCategoryResponseDto {
    private String categoryCode;

    private String categoryName;

    public ChildCategoryResponseVo toVo(){
        return ChildCategoryResponseVo
                .builder()
                .categoryCode(categoryCode)
                .categoryName(categoryName)
                .build();
    }

    @QueryProjection
    public ChildCategoryResponseDto(String categoryCode, String categoryName) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
    }
}
