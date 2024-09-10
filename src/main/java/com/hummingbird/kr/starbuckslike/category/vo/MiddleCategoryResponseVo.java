package com.hummingbird.kr.starbuckslike.category.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MiddleCategoryResponseVo {

    private String middleCategoryCode;
    private String middleCategoryName;
    private String middleCategoryDescription;
    private String topCategoryCode;

}
