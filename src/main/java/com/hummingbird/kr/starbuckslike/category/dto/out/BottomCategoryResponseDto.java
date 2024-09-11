package com.hummingbird.kr.starbuckslike.category.dto.out;

import com.hummingbird.kr.starbuckslike.category.vo.BottomCategoryResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BottomCategoryResponseDto {

    private String bottomCategoryCode;
    private String bottomCategoryName;
    private String bottomCategoryDescription;
    private String middleCategoryCode;

    public BottomCategoryResponseVo toVo() {
        return BottomCategoryResponseVo.builder()
                .bottomCategoryCode(bottomCategoryCode)
                .bottomCategoryName(bottomCategoryName)
                .bottomCategoryDescription(bottomCategoryDescription)
                .middleCategoryCode(middleCategoryCode)
                .build();
    }

}
