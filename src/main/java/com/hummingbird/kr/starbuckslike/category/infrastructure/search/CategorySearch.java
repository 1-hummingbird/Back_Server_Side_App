package com.hummingbird.kr.starbuckslike.category.infrastructure.search;

import com.hummingbird.kr.starbuckslike.category.dto.out.ChildCategoryResponseDto;
import com.hummingbird.kr.starbuckslike.category.dto.out.MainCategoryResponseDto;
import com.hummingbird.kr.starbuckslike.category.dto.out.MiddleCategoryResponseDto;

import java.util.List;

public interface CategorySearch {
    // 메인 카테고리 조회
    List<MainCategoryResponseDto> findMainCategoryResponseDto();

    // 대 카테고리의 하위 카테고리(중) 조회
    List<ChildCategoryResponseDto> findChildCategoriesByTopCategory(String categoryCode);

}
