package com.hummingbird.kr.starbuckslike.temp.repository.search;

import com.hummingbird.kr.starbuckslike.temp.domain.Product;

import java.util.List;

public interface ProductSearch {
    // path로 상품 검색
    List<Product> findProductsByPath(String path);
}
