package com.hummingbird.kr.starbuckslike.temp.repository.search;

import com.hummingbird.kr.starbuckslike.temp.domain.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CategorySearch {
    //  깊이==depth 안 카테고리들만 검색.  (최상위 카테고리는 0)
    List<Category> findCategoryByDepth(Integer depth);
}
