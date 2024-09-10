package com.hummingbird.kr.starbuckslike.category.dto.in;

import com.hummingbird.kr.starbuckslike.category.domain.MiddleCategory;
import com.hummingbird.kr.starbuckslike.category.domain.TopCategory;
import lombok.*;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MiddleCategoryRequestDto {

    private String middleCategoryName;
    private String middleCategoryDescription;
    private String topCategoryCode;

    public MiddleCategory toEntity(TopCategory topCategory, String middleCategoryCode) {
        return MiddleCategory.builder()
                .categoryName(middleCategoryName)
                .categoryDescription(middleCategoryDescription)
                .categoryCode(middleCategoryCode)
                .topCategory(topCategory)
                .build();
    }

}
