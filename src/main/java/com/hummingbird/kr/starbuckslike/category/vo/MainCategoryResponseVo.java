package com.hummingbird.kr.starbuckslike.category.vo;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MainCategoryResponseVo {
    private String categoryCode;

    private String imageUrl;

    private String categoryName;
}
