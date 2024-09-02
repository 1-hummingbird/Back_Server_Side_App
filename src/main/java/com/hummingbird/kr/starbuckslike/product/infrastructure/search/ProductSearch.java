package com.hummingbird.kr.starbuckslike.product.infrastructure.search;

import com.hummingbird.kr.starbuckslike.product.domain.Product;
import com.hummingbird.kr.starbuckslike.product.dto.ProductListDto;
import com.hummingbird.kr.starbuckslike.product.infrastructure.condition.ProductCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductSearch {


    // 카테고리 path로 상품 검색
    List<Product> findProductsByPath(String path);

    // 기획전에 해당하는 상품 리스트 조회
    List<ProductListDto> findProductListById(Long exhibitionId);


    /**
     * 상품 리스트 조회
     * 필터링 조건[카테고리,가격,기획전] , 정렬 조건(최신순, 높은 가격, 낮은 가격)
     * offset based
     */
    Page<ProductListDto> searchProductListPageV1(ProductCondition productCondition, Pageable pageable);


}
