package com.hummingbird.kr.starbuckslike.product.presentation;


import com.hummingbird.kr.starbuckslike.common.entity.CommonResponseEntity;
import com.hummingbird.kr.starbuckslike.product.application.ProductService;
import com.hummingbird.kr.starbuckslike.product.dto.ProductDetailDto;
import com.hummingbird.kr.starbuckslike.product.dto.ProductImageDto;
import com.hummingbird.kr.starbuckslike.product.dto.ProductListDto;
import com.hummingbird.kr.starbuckslike.product.dto.RequestProductInfoDto;
import com.hummingbird.kr.starbuckslike.product.infrastructure.condition.ProductCondition;
import com.hummingbird.kr.starbuckslike.product.infrastructure.search.ProductSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ProductController {

    private final ProductService productService;


    // todo Vo 적용

    // 상품 디테일 상품 상품명 가격 등 조회
    @GetMapping("/product/info/{productId}")
    public CommonResponseEntity<RequestProductInfoDto> findProductInfoByIdV1(
            @PathVariable("productId") Long productId){
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "상품 정보 조회 성공",
                productService.findProductInfoById(productId)
        );
    }

    // 상품 디테일 상세정보(에티터 html) 조회
    @GetMapping("/product/detail/{productId}")
    public CommonResponseEntity<ProductDetailDto> findProductDetailDtoByIdV1(
            @PathVariable("productId") Long productId){
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "상품 상세정보 조회 성공",
                productService.findProductDetailDtoById(productId)
        );
    }
    // 상품에 포함된 옵션 정보 조회
    @GetMapping("/product/image/{productId}")
    public CommonResponseEntity<List<ProductImageDto>> findProductImageDtoByIdV1(
            @PathVariable("productId") Long productId){
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "상품 이미지 조회 성공",
                productService.findProductImageDtoById(productId)
        );
    }


    //public List<ProductImageDto> findProductImageDtoById(Long productId);


    // 상품 리스트 조회 [가격,카테고리]
//    @GetMapping("/products")
//    public Page<ProductListDto> searchMemberV1(ProductCondition condition, Pageable pageable) {
//        return productSearch.searchProductListPageV1(condition, pageable);
//    }



}
