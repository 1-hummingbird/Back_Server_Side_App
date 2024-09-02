package com.hummingbird.kr.starbuckslike.category.infrastructure.search;

import com.hummingbird.kr.starbuckslike.category.dto.CategoryListDto;

import java.util.List;

public interface CategorySearch {
    //  깊이==depth 안 카테고리들만 검색.  (최상위 카테고리는 0)
    List<CategoryListDto> findCategoryByDepth(Integer depth);

}
