package com.hummingbird.kr.starbuckslike.product.application;

import com.hummingbird.kr.starbuckslike.common.Exception.BaseException;
import com.hummingbird.kr.starbuckslike.common.entity.BaseResponseStatus;
import com.hummingbird.kr.starbuckslike.product.domain.Product;
import com.hummingbird.kr.starbuckslike.product.domain.Wish;
import com.hummingbird.kr.starbuckslike.product.dto.out.*;
import com.hummingbird.kr.starbuckslike.product.infrastructure.ProductRepository;
import com.hummingbird.kr.starbuckslike.product.infrastructure.condition.ProductCondition;
import com.hummingbird.kr.starbuckslike.product.infrastructure.search.ProductSearch;
import com.hummingbird.kr.starbuckslike.product.infrastructure.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements  ProductService{
    private final ProductSearch productSearch;
    private final ProductRepository productRepository;
    private final WishRepository wishRepository;
    @Override
    public ProductInfoResponseDto findProductInfoById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));
        return productSearch.findProductInfoById(product.getId());
    }

    @Override
    public ProductInfoResponseDto findProductInfoByIdV2(Long productId, String memberUid) {
        return productSearch.findProductInfoByIdV2(productId, memberUid);
    }

    @Override
    public ProductDetailResponseDto findProductDetailDtoById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));
        return productSearch.findProductDetailDtoById(product.getId());
    }

    @Override
    public List<ProductImageResponseDto> findProductImageDtoById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));
        return productSearch.findProductImageDtoById(product.getId());
    }

    @Override
    public List<ProductOptionResponseDto> findProductOptionDtoById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));
        return productSearch.findProductOptionDtoById(product.getId());
    }

    @Override
    public List<Long> findProductIdListByExhibitionId(Long exhibitionId) {
        return productSearch.findProductIdListByExhibitionId(exhibitionId);
    }

    @Override
    public ProductListResponseDto findProductListDtoByProductId(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));
        return productSearch.findProductListDtoByProductId(product.getId());
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
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));
        return productSearch.findProductListImageResponseDtoById(product.getId());
    }

    @Override
    public ProductListInfoResponseDto findProductListInfoResponseDtoById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));
        return productSearch.findProductListInfoResponseDtoById(product.getId());
    }

    @Override
    public void updateWishStatus(String memberUID, Long productId) {
        productRepository.findById(productId).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));
        Optional<Wish> existingWish = wishRepository.findWishByMemberUIDAndProductId(memberUID, productId);
        if (existingWish.isPresent()) { // 기존에 위시 등록한 상품인지 확인
            // 이미 존재하면 isWished 값을 토글
            Wish wish = existingWish.get();
            wish.toggleWishStatus();
            wishRepository.save(wish);
        }
        else { // 없으면 새로 추가
            Wish newWish = Wish.builder()
                    .memberUID(memberUID)
                    .productId(productId)
                    .isWished(true) // 최초 추가 시 true
                    .build();
            wishRepository.save(newWish);
        }
    }

    @Override
    public Slice<Long> searchWishProductIdsV1(Pageable pageable, String memberUid) {
        return productSearch.searchWishProductIdsV1(pageable,memberUid);
    }

}
