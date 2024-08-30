package com.hummingbird.kr.starbuckslike.temp.controller;

import com.hummingbird.kr.starbuckslike.temp.dto.ProductListDto;
import com.hummingbird.kr.starbuckslike.temp.repository.condition.ProductCondition;
import com.hummingbird.kr.starbuckslike.temp.repository.search.ProductSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
public class ProductController {

    private  final ProductSearch productSearch;

    // 상품 리스트 조회 [가격,카테고리]
    @GetMapping("/api/v1/products")
    public Page<ProductListDto> searchMemberV3(ProductCondition condition, Pageable pageable) {
        return productSearch.searchProductListPageV1(condition, pageable);
    }

}