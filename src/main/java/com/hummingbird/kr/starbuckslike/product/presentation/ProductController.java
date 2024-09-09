package com.hummingbird.kr.starbuckslike.product.presentation;


import com.hummingbird.kr.starbuckslike.common.entity.CommonResponseEntity;
import com.hummingbird.kr.starbuckslike.common.entity.CommonResponseMessage;
import com.hummingbird.kr.starbuckslike.product.application.ProductService;
import com.hummingbird.kr.starbuckslike.product.dto.out.*;
import com.hummingbird.kr.starbuckslike.product.infrastructure.condition.ProductCondition;
import com.hummingbird.kr.starbuckslike.product.infrastructure.search.ProductSearch;
import com.hummingbird.kr.starbuckslike.product.vo.*;
import io.swagger.v3.oas.annotations.Operation;
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
import java.util.stream.Collectors;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;
    /**
     * 상품 디테일
     */
    // 상품 디테일 상품 상품명 가격 등 조회
    @Operation(summary = "상품 디테일 조회", description = "상품 id로 상품 디테일(상품명,가격,할인 등) 조회")
    @GetMapping("/info/{productId}")
    public CommonResponseEntity<ProductInfoResponseVo> findProductInfoByIdV1(
            @PathVariable("productId") Long productId){
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                productService.findProductInfoById(productId).toVo()
        );
    }
    // 상품 디테일 상세정보(에티터 html) 조회
    @Operation(summary = "상품 상세정보 조회", description = "상품 상세정보(에디터) 조회")
    @GetMapping("/detail/{productId}")
    public CommonResponseEntity<ProductDetailResponseVo> findProductDetailDtoByIdV1(
            @PathVariable("productId") Long productId){
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                productService.findProductDetailDtoById(productId).toVo()
        );
    }
    // 상품의 이미지 조회
    @Operation(summary = "상품 이미지 조회", description = "상품 id로 상품 이미지 조회")
    @GetMapping("/images/{productId}")
    public CommonResponseEntity<List<ProductImageResponseVo>> findProductImageDtoByIdV1(
            @PathVariable("productId") Long productId){
        // map 으로 Vo 변환
        List<ProductImageResponseVo> responseVoList =
                productService.findProductImageDtoById(productId)
                    .stream()
                    .map(ProductImageResponseDto::toVo)
                    .collect(Collectors.toList());

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                responseVoList
        );
    }
    // 상품 옵션 조회
    @Operation(summary = "상품 옵션 조회", description = "상품 id로 상품 옵션 조회")
    @GetMapping("/options/{productId}")
    public CommonResponseEntity<List<ProductOptionResponseVo>> findProductOptionDtoByIdV1(
            @PathVariable("productId") Long productId){

        List<ProductOptionResponseVo> responseVoList =
                productService.findProductOptionDtoById(productId)
                        .stream()
                        .map(ProductOptionResponseDto::toVo)
                        .collect(Collectors.toList());

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                responseVoList
        );
    }

    /**
     * 기획전에 해당하는 상품 리스트
     */
    // 기획전으로 상품 id 리스트 조회
    @Operation(summary = "기획전 상품 리스트", description = "기획전에 해당하는 상품 id 리스트 조회")
    @GetMapping("/list/exhibition/{exhibitionId}")
    public CommonResponseEntity<List<Long>> findProductIdListByExhibitionIdV1(
            @PathVariable("exhibitionId") Long exhibitionId){
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                productService.findProductIdListByExhibitionId(exhibitionId)
        );
    }
    // 기획전에 해당하는 상품 id로 단건 조회
    @Operation(summary = "기획전 상품 단건 조회", description = "기획전에 해당하는 상품 id로 단건 조회")
    @GetMapping("/{productId}")
    public CommonResponseEntity<ProductListResponseVo> findProductListDtoByProductIdV1(
            @PathVariable("productId") Long productId){
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                productService.findProductListDtoByProductId(productId).toVo()
        );
    }


    /**
     *
     */



}
