package com.hummingbird.kr.starbuckslike.product.infrastructure.search;

import com.hummingbird.kr.starbuckslike.product.domain.Product;
import com.hummingbird.kr.starbuckslike.product.dto.ProductDetailDto;
import com.hummingbird.kr.starbuckslike.product.dto.ProductImageDto;
import com.hummingbird.kr.starbuckslike.product.dto.ProductListDto;
import com.hummingbird.kr.starbuckslike.product.dto.RequestProductInfoDto;
import com.hummingbird.kr.starbuckslike.product.infrastructure.condition.ProductCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductSearch {


    // todo 상품엔티티에 카테고리 정보 제거해서 다시 해야함
    // 카테고리 path로 상품 검색
    //List<Product> findProductsByPath(String path);


    // 기획전에 해당하는 상품 리스트 조회
    List<ProductListDto> findProductListDtoByExhibitionId(Long exhibitionId);
    // 개선
    List<Long> findProductIdListByExhibitionId(Long exhibitionId);

    /**
     * 상품 리스트 조회
     * 필터링 조건[카테고리,가격,기획전] , 정렬 조건(최신순, 높은 가격, 낮은 가격)
     * offset based
     */
    //Page<ProductListDto> searchProductListPageV1(ProductCondition productCondition, Pageable pageable);


    /*
        ---------------------
        여기서 부터 상품 디테일 정보 (상품상세 , 상품의 이미지)
        ---------------------
     */

    //상품 디테일 상품 이미지+상품명 가격 등 조회
    RequestProductInfoDto findProductInfoById(Long productId);

    // 상품 id로 상품 상세정보(에티터 html)  조회
    ProductDetailDto findProductDetailDtoById(Long productId);

    // 상품 Id로 상품 이미지 조회
    List<ProductImageDto> findProductImageDtoById(Long productId);




}
