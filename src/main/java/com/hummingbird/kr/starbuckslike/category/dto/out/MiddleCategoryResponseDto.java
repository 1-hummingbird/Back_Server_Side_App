package com.hummingbird.kr.starbuckslike.category.dto.out;

import com.hummingbird.kr.starbuckslike.category.vo.MiddleCategoryResponseVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
public class MiddleCategoryResponseDto {

    private String middleCategoryName;
    private String middleCategoryDescription;
    private String middleCategoryCode;
    private String topCategoryCode;

    public MiddleCategoryResponseVo toVo() {
        return MiddleCategoryResponseVo.builder()
                .middleCategoryName(middleCategoryName)
                .middleCategoryDescription(middleCategoryDescription)
                .middleCategoryCode(middleCategoryCode)
                .topCategoryCode(topCategoryCode)
                .build();
    }
    @QueryProjection
    public MiddleCategoryResponseDto(String middleCategoryName, String middleCategoryDescription,
                                     String middleCategoryCode, String topCategoryCode) {
        this.middleCategoryName = middleCategoryName;
        this.middleCategoryDescription = middleCategoryDescription;
        this.middleCategoryCode = middleCategoryCode;
        this.topCategoryCode = topCategoryCode;
    }
}
