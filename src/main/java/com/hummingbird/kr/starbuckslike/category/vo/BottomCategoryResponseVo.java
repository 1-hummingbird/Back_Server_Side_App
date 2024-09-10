package com.hummingbird.kr.starbuckslike.category.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BottomCategoryResponseVo {

    private String bottomCategoryCode;
    private String bottomCategoryName;
    private String bottomCategoryDescription;
    private String middleCategoryCode;

}
