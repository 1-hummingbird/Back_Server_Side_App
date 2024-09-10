package com.hummingbird.kr.starbuckslike.category.infrastructure.search;

import com.hummingbird.kr.starbuckslike.category.dto.out.MainCategoryResponseDto;

import java.util.List;

public interface CategorySearch {
    // 메인 카테고리 조회
    List<MainCategoryResponseDto> findMainCategoryResponseDto();
}
