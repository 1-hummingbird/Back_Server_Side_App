package com.hummingbird.kr.starbuckslike.product.infrastructure.search;

import com.hummingbird.kr.starbuckslike.product.dto.out.*;
import java.util.List;

public interface ProductSearch {


    /**
     * 기획전에 해당하는 상품 리스트 조회
     */
    // 개선 - 기획전에 해당하는 상품 리스트 조회
    List<Long> findProductIdListByExhibitionId(Long exhibitionId);
    ProductListResponseDto findProductListDtoByProductId(Long productId);


    /**
     * 상품 리스트 조회
     * 필터링 조건[카테고리,가격,기획전] , 정렬 조건(최신순, 높은 가격, 낮은 가격)
     * offset based
     */
    //Page<ProductListDto> searchProductListPageV1(ProductCondition productCondition, Pageable pageable);
    // 정렬만 적용

    /**
     *  상품 디테일 정보 (상품상세 , 상품의 이미지)
     */
    //상품 디테일 상품 이미지+상품명 가격 등 조회
    ProductInfoResponseDto findProductInfoById(Long productId);

    // 상품 id로 상품 상세정보(에티터 html)  조회
    ProductDetailResponseDto findProductDetailDtoById(Long productId);

    // 상품 Id로 상품 이미지 조회
    List<ProductImageResponseDto> findProductImageDtoById(Long productId);

    // 상품의 옵션 조회
    List<ProductOptionResponseDto> findProductOptionDtoById(Long productId);

}
