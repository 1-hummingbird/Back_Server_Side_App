package com.hummingbird.kr.starbuckslike.product.application;

import com.hummingbird.kr.starbuckslike.product.dto.out.*;
import com.hummingbird.kr.starbuckslike.product.infrastructure.condition.ProductCondition;
import com.hummingbird.kr.starbuckslike.product.infrastructure.search.ProductSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements  ProductService{
    private final ProductSearch productSearch;
    @Override
    public ProductInfoResponseDto findProductInfoById(Long productId) {
        return productSearch.findProductInfoById(productId);
    }

    @Override
    public ProductDetailResponseDto findProductDetailDtoById(Long productId) {
        return productSearch.findProductDetailDtoById(productId);
    }

    @Override
    public List<ProductImageResponseDto> findProductImageDtoById(Long productId) {
        return productSearch.findProductImageDtoById(productId);
    }

    @Override
    public List<ProductOptionResponseDto> findProductOptionDtoById(Long productId) {
        return productSearch.findProductOptionDtoById(productId);
    }

    @Override
    public List<Long> findProductIdListByExhibitionId(Long exhibitionId) {
        return productSearch.findProductIdListByExhibitionId(exhibitionId);
    }

    @Override
    public ProductListResponseDto findProductListDtoByProductId(Long productId) {
        return productSearch.findProductListDtoByProductId(productId);
    }

    @Override
    public Page<ProductListResponseDto> searchProductListPageV1(ProductCondition productCondition, Pageable pageable) {
        return productSearch.searchProductListPageV1(productCondition,pageable);
    }

    @Override
    public Slice<Long> searchProductIdsV1(ProductCondition productCondition, Pageable pageable) {
        return productSearch.searchProductIdsV1(productCondition,pageable);

    }

    @Override
    public ProductListImageResponseDto findProductListImageResponseDtoById(Long productId) {
        return productSearch.findProductListImageResponseDtoById(productId);
    }

    @Override
    public ProductListInfoResponseDto findProductListInfoResponseDtoById(Long productId) {
        return productSearch.findProductListInfoResponseDtoById(productId);
    }

}
