package com.hummingbird.kr.starbuckslike.category.infrastructure.search;

import com.hummingbird.kr.starbuckslike.category.dto.CategoryListDto;
import com.hummingbird.kr.starbuckslike.category.dto.MainCategoryListDto;

import java.util.List;

public interface CategorySearch {
    //  깊이로 카테고리 검색
    List<CategoryListDto> findCategoryByDepth(Integer depth);

    // 최상위 부모 카테고리만 출력
    List<MainCategoryListDto> findAllRootCategory();
}
