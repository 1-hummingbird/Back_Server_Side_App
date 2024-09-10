package com.hummingbird.kr.starbuckslike.product.application;

import com.hummingbird.kr.starbuckslike.product.dto.out.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface ProductService {

    /**
     * 상품 디테일
     */
    // 상품 디테일 상품 상품명 가격 등 조회
    public ProductInfoResponseDto findProductInfoById(Long productId);
    // 상품 디테일 상세정보(에티터 html) 조회
    public ProductDetailResponseDto findProductDetailDtoById(Long productId);
    // 상품에 포함된 이미지 정보 조회
    public List<ProductImageResponseDto> findProductImageDtoById(Long productId);
    // 상품에 포함된 옵션 정보 조회
    List<ProductOptionResponseDto> findProductOptionDtoById(Long productId);

    /**
     * 상품 리스트
     */
    // 기획전에 해당하는 상품 리스트
    List<Long> findProductIdListByExhibitionId(Long exhibitionId);
    // 기획전에 해당하는 상품 id로 단건 조회
    ProductListResponseDto findProductListDtoByProductId(Long productId);


}
