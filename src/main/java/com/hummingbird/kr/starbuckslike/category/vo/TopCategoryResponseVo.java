package com.hummingbird.kr.starbuckslike.category.vo;

import lombok.*;

@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TopCategoryResponseVo {

    private String topCategoryCode;
    private String topCategoryName;
    private String topCategoryDescription;

}
