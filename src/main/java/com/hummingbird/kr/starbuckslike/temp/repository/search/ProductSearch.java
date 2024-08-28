package com.hummingbird.kr.starbuckslike.temp.repository.search;

import com.hummingbird.kr.starbuckslike.temp.domain.Product;
import com.hummingbird.kr.starbuckslike.temp.dto.ProductListDto;

import java.util.List;

public interface ProductSearch {


    // 카테고리 path로 상품 리스트 검색
    List<Product> findProductsByPath(String path);

    // todo : 상품 데이터가 많을 경우 Page로 return 할지 고민
    // 기획전에 해당하는 상품 리스트 조회
    List<ProductListDto> findProductListById(Long exhibitionId);

}
