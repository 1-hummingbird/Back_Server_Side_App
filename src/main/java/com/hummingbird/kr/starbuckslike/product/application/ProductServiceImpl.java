package com.hummingbird.kr.starbuckslike.product.application;

import com.hummingbird.kr.starbuckslike.product.domain.Wish;
import com.hummingbird.kr.starbuckslike.product.dto.out.*;
import com.hummingbird.kr.starbuckslike.product.infrastructure.ProductRepository;
import com.hummingbird.kr.starbuckslike.product.infrastructure.condition.ProductCondition;
import com.hummingbird.kr.starbuckslike.product.infrastructure.search.ProductSearch;
import com.hummingbird.kr.starbuckslike.purchase.infrastructure.WishRepository;
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

    @Override
    public void updateWishStatus(String memberUid, Long productId) {
        productRepository.findById(productId).orElseThrow(
                () -> new IllegalArgumentException("상품 번호가 잘못되었습니다."));
        Optional<Wish> existingWish = wishRepository.findWishByMemberUidAndProductId(memberUid, productId);
        if (existingWish.isPresent()) { // 기존에 위시 등록한 상품인지 확인
            // 이미 존재하면 isWished 값을 토글
            Wish wish = existingWish.get();
            wish.toggleWishStatus();
            wishRepository.save(wish);
        }
        else { // 없으면 새로 추가
            Wish newWish = Wish.builder()
                    .memberUid(memberUid)
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
