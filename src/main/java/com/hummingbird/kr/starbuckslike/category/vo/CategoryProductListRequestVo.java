package com.hummingbird.kr.starbuckslike.category.vo;

import lombok.Getter;

@Getter
public class CategoryProductListRequestVo {

    private String topCategoryCode;
    private String middleCategoryCode;
    private String bottomCategoryCode;
    private Long productId;

}
