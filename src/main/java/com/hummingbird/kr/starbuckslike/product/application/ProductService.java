package com.hummingbird.kr.starbuckslike.product.application;

import com.hummingbird.kr.starbuckslike.common.utils.CursorPage;
import com.hummingbird.kr.starbuckslike.product.dto.out.*;
import com.hummingbird.kr.starbuckslike.product.infrastructure.condition.ProductCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface ProductService {

    /**
     * 상품 디테일
     */
    // 상품 디테일 상품 상품명 가격 등 조회
    ProductInfoResponseDto findProductInfoById(Long productId);
    ProductInfoResponseDto findProductInfoByIdV2(Long productId, String memberUid);
    // 상품 디테일 상세정보(에티터 html) 조회
    ProductDetailResponseDto findProductDetailDtoById(Long productId);
    // 상품에 포함된 이미지 정보 조회
    List<ProductImageResponseDto> findProductImageDtoById(Long productId);
    // 상품에 포함된 옵션 정보 조회
    List<ProductOptionResponseDto> findProductOptionDtoById(Long productId);

    /**
     *  기획전 상품 리스트
     */
    // 기획전에 해당하는 상품 리스트
    List<Long> findProductIdListByExhibitionId(Long exhibitionId);
    // 기획전에 해당하는 상품 id로 단건 조회
    ProductListResponseDto findProductListDtoByProductId(Long productId);

    /**
     *  메인 상품 리스트 필터링[카테고리,가격] , 정렬 조건 적용
     */
    Page<ProductListResponseDto> searchProductListPageV1(ProductCondition productCondition, Pageable pageable);
    Slice<Long> searchProductIdsV1(ProductCondition productCondition, Pageable pageable);
    CursorPage<Long> searchProductIdsCursorBase(ProductCondition productCondition, Long lastId,
                                                Integer pageSize,
                                                Integer page);

    // 상품아이디로 상품 이미지 단건 조회
    ProductListImageResponseDto findProductListImageResponseDtoById(Long productId);
    // 상품아이디로 상품 리스트 정보 단건 조회
    ProductListInfoResponseDto findProductListInfoResponseDtoById(Long productId);
    //

    /**
     * 상품 위시리스트
     */
    // 상품 위시리스트 활성,비활성
    @Transactional
    void updateWishStatus(String memberUid , Long productId);

    // 회원의 위시리스트 상품 조회
    Slice<Long> searchWishProductIdsV1(Pageable pageable, String memberUid);




}
