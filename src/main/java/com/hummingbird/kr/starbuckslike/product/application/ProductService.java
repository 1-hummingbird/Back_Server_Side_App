package com.hummingbird.kr.starbuckslike.product.application;

import com.hummingbird.kr.starbuckslike.product.dto.ProductDetailDto;
import com.hummingbird.kr.starbuckslike.product.dto.ProductImageDto;
import com.hummingbird.kr.starbuckslike.product.dto.RequestProductInfoDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface ProductService {

    /**
     * 상품 디테일
     */
    // 상품 디테일 상품 상품명 가격 등 조회
    public RequestProductInfoDto findProductInfoById(Long productId);
    // 상품 디테일 상세정보(에티터 html) 조회
    public ProductDetailDto findProductDetailDtoById(Long producㅊtId);
    // 상품에 포함된 옵션 정보 조회
    public List<ProductImageDto> findProductImageDtoById(Long productId);

}
