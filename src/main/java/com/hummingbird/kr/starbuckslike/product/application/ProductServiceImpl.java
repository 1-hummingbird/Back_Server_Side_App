package com.hummingbird.kr.starbuckslike.product.application;

import com.hummingbird.kr.starbuckslike.product.dto.ProductDetailDto;
import com.hummingbird.kr.starbuckslike.product.dto.ProductImageDto;
import com.hummingbird.kr.starbuckslike.product.dto.RequestProductInfoDto;
import com.hummingbird.kr.starbuckslike.product.infrastructure.search.ProductSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements  ProductService{
    private final ProductSearch productSearch;
    @Override
    public RequestProductInfoDto findProductInfoById(Long productId) {
        return productSearch.findProductInfoById(productId);
    }

    @Override
    public ProductDetailDto findProductDetailDtoById(Long productId) {
        return productSearch.findProductDetailDtoById(productId);
    }

    @Override
    public List<ProductImageDto> findProductImageDtoById(Long productId) {
        return productSearch.findProductImageDtoById(productId);
    }
}
