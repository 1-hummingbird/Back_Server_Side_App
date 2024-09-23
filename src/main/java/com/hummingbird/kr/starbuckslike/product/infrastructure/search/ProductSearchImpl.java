package com.hummingbird.kr.starbuckslike.product.infrastructure.search;

import com.hummingbird.kr.starbuckslike.cart.domain.QCart;
import com.hummingbird.kr.starbuckslike.category.domain.QCategoryProductList;
import com.hummingbird.kr.starbuckslike.product.domain.QProduct;
import com.hummingbird.kr.starbuckslike.product.domain.QProductImage;
import com.hummingbird.kr.starbuckslike.product.domain.QProductOption;
import com.hummingbird.kr.starbuckslike.product.domain.QWish;
import com.hummingbird.kr.starbuckslike.product.dto.out.*;
import com.hummingbird.kr.starbuckslike.product.infrastructure.condition.OrderCondition;
import com.hummingbird.kr.starbuckslike.product.infrastructure.condition.PriceType;
import com.hummingbird.kr.starbuckslike.product.infrastructure.condition.ProductCondition;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Constructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.hummingbird.kr.starbuckslike.cart.domain.QCart.cart;
import static com.hummingbird.kr.starbuckslike.category.domain.QCategoryProductList.categoryProductList;
import static com.hummingbird.kr.starbuckslike.exhibition.domain.QExhibitionProduct.exhibitionProduct;
import static com.hummingbird.kr.starbuckslike.product.domain.QProduct.product;
import static com.hummingbird.kr.starbuckslike.product.domain.QProductImage.productImage;
import static com.hummingbird.kr.starbuckslike.product.domain.QProductOption.productOption;
import static com.hummingbird.kr.starbuckslike.product.domain.QWish.wish;

/**
 * 상품 조회  (querydsl 등 조회 쿼리, JpaRepository와 따로 두었음)
 * 지금은 엔티티로 받는데 나중에 DTO나 VO로 받는 부분 추가해야 함
 * @author 허정현
 */
@Repository
@RequiredArgsConstructor
@Log4j2
public class ProductSearchImpl implements ProductSearch {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Long> findProductIdListByExhibitionId(Long exhibitionId) {
        return queryFactory
                .selectDistinct(product.id) //
                .from(product)
                // 상품 id 와 기획전상품(중간 태이블)의 id 조인
                .join(exhibitionProduct).on(product.id.eq(exhibitionProduct.productId))
                // 특정 기획전 상품에 (exhibitionId) 에 해당하는 상품만 조회
                .where(
                        exhibitionProduct.exhibition.id.eq(exhibitionId)
                        .and(product.isDeleted.eq(false))
                )
                .fetch();
    }

    @Override
    public ProductListResponseDto findProductListDtoByProductId(Long productId) {
        return queryFactory
                .select( new QProductListResponseDto( Expressions.asNumber(productId).as("id") ,
                        productImage.imageUrl, product.name, product.price,
                        new CaseBuilder()
                                .when(isNewCondition)
                                    .then(true)
                                    .otherwise(false),
                        product.isDiscounted, product.discountRate, product.isAvailable
                ))
                .from(product)
                .leftJoin(productImage)
                .on(
                    product.id.eq(productImage.product.id)
                    .and(productImage.seq.eq(0)) // 상품의 대표이미지만 가져옴
                )
                .where(product.id.eq(productId))
                .fetchOne();
    }

    @Override
    public Page<ProductListResponseDto> searchProductListPageV1(ProductCondition productCondition, Pageable pageable) {
        List<ProductListResponseDto> fetch = queryFactory
                .select(
                        new QProductListResponseDto(product.id, productImage.imageUrl, product.name, product.price,
                                new CaseBuilder() // sql case문
                                        .when(isNewCondition).then(true)
                                        .otherwise(false),
                                product.isDiscounted, product.discountRate, product.isAvailable
                        )
                )
                .from(product)
                .leftJoin(productImage)
                .on(product.id.eq(productImage.id)
                        .and(productImage.seq.eq(0))) // 메인 이미지만 가져옴
                .leftJoin(categoryProductList).on(product.id.eq(categoryProductList.id))
                .where(
                        priceRangeCondition(productCondition.getPriceType()), // 가격 필터링
                        topCategoryCodeCondition(productCondition.getTopCode()),  // 상 카테고리 필터링
                        middleCategoryCodeCondition(productCondition.getMiddleCode()), // 중 카테고리 필터링
                        product.isDeleted.eq(false)
                )
                .orderBy(
                        getOrderSpecifier(productCondition.getOrderCondition()) // 정렬 조건
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        // 카운트 쿼리
        JPAQuery<Long> countQuery = queryFactory
                .select(product.count())
                .from(product)
                .leftJoin(categoryProductList).on(product.id.eq(categoryProductList.id))
                .where(
                        priceRangeCondition(productCondition.getPriceType()), // 가격 필터링
                        topCategoryCodeCondition(productCondition.getTopCode()),  // 상 카테고리 필터링
                        middleCategoryCodeCondition(productCondition.getMiddleCode()) , // 중 카테고리 필터링
                        product.isDeleted.eq(false)
                );

        return PageableExecutionUtils.getPage(fetch, pageable, countQuery::fetchOne) ;
    }

    @Override
    public Slice<Long> searchProductIdsV1(ProductCondition productCondition, Pageable pageable) {
        List<Long> fetch = queryFactory
                .select(categoryProductList.id)
                .from(categoryProductList)
                .join(categoryProductList).on(categoryProductList.product.id.eq(product.id))
                .where(
                        topCategoryCodeCondition(productCondition.getTopCode()),  // 상 카테고리 필터링
                        middleCategoryCodeCondition(productCondition.getMiddleCode()), // 중 카테고리 필터링
                        productNameCondition(productCondition.getProductName()), // %상품명% 필터링
                        product.isDeleted.eq(false)
                )
                .orderBy(
                        getOrderSpecifier(productCondition.getOrderCondition()) // 정렬 조건
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1) // 다음 페이지가 있는지 확인하기 위해 1개 더 가져옴
                .fetch();

        // 다음 페이지 여부 확인
        boolean hasNext = fetch.size() > pageable.getPageSize();
        // Slice 크기에 맞게 자르기
        if (hasNext) {
            fetch.remove(fetch.size() - 1); // 페이징 사이즈 + 1 만큼 가져왔으므로 마지막 한 개 제거
        }

        return new SliceImpl<>(fetch, pageable, hasNext);
    }

    @Override
    public ProductListImageResponseDto findProductListImageResponseDtoById(Long productId) {
        return queryFactory
                .select(new QProductListImageResponseDto(
                                Expressions.asNumber(productId).as("productId"), productImage.imageUrl )
                )
                .from(productImage)
                .where(
                        productImage.product.id.eq(productId)
                        .and(productImage.seq.eq(0))
                )
                .fetchOne();
    }

    @Override
    public ProductListInfoResponseDto findProductListInfoResponseDtoById(Long productId) {
        return queryFactory
                .select(new QProductListInfoResponseDto(
                        product.name,
                        new CaseBuilder() // sql case문
                                .when(isNewCondition).then(true)
                                .otherwise(false),
                        product.isDiscounted, product.discountRate
                        )
                )
                .from(product)
                .where(product.id.eq(productId))
                .fetchOne();
    }

    @Override
    public Slice<Long> searchWishProductIdsV1(Pageable pageable , String memberUID) {
        List<Long> fetch = queryFactory
                .select(wish.productId)
                .from(wish)
                .join(product).on(wish.productId.eq(product.id))
                .where(
                        product.isDeleted.isFalse()
                        .and(wish.memberUID.eq(memberUID))
                        .and(wish.isWished.isTrue())
                )
                .orderBy(wish.updatedAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1) // 다음 페이지가 있는지 확인하기 위해 1개 더 가져옴
                .fetch();

        boolean hasNext = fetch.size() > pageable.getPageSize();
        if (hasNext) {
            fetch.remove(fetch.size() - 1); // 페이징 사이즈 + 1 만큼 가져왔으므로 마지막 한 개 제거
        }
        return new SliceImpl<>(fetch, pageable, hasNext);
    }


    @Override
    public ProductInfoResponseDto findProductInfoById(Long productId) {
        return queryFactory
                .select(new QProductInfoResponseDto(product.name,
                            new CaseBuilder() // sql case문
                                .when(isNewCondition).then(true)
                                .otherwise(false),
                            product.shortDescription, product.isDiscounted , product.discountRate
                        ))
                .from(product)
                .where(
                        product.id.eq(productId)
                        .and(product.isDeleted.eq(false))
                )
                .fetchOne();
    }

    @Override
    public ProductInfoResponseDto findProductInfoByIdV2(Long productId, String memberUid) {
        ProductInfoResponseDto productInfoResponseDto = queryFactory
                .select(new QProductInfoResponseDto(product.name,
                        new CaseBuilder() // sql case문
                                .when(isNewCondition).then(true)
                                .otherwise(false),
                        product.shortDescription, product.isDiscounted, product.discountRate
                ))
                .from(product)
                .where(
                        product.id.eq(productId)
                                .and(product.isDeleted.eq(false))
                )
                .fetchOne();
        // 해당 상품이 장바구니 담긴 개수
        Long cartItemQuantity = 0L;
        if(memberUid != null && !memberUid.isEmpty()){
            cartItemQuantity = queryFactory.select(cart.count())
                    .from(cart)
                    .where(
                            cart.isDeleted.isFalse()
                            .and(cart.memberUID.eq(memberUid))
                            .and(cart.productId.eq(productId))
                    ).fetchOne();
        }
        assert productInfoResponseDto != null;
        productInfoResponseDto.setCartCount(cartItemQuantity);

        return productInfoResponseDto;
    }

    @Override
    public ProductDetailResponseDto findProductDetailDtoById(Long productId) {
        return queryFactory.select(new QProductDetailResponseDto(product.fullDescription))
                .from(product)
                .where(
                        product.id.eq(productId)
                        .and(product.isDeleted.eq(false))
                )
                .fetchOne();

    }

    @Override
    public List<ProductImageResponseDto> findProductImageDtoById(Long productId) {
        return queryFactory.select(new QProductImageResponseDto(productImage.imageUrl,
                                        new CaseBuilder()
                                                .when(isMainCondition).then(true)
                                                .otherwise(false)
                                        )
                )
                .from(productImage)
                .where(
                        productImage.product.id.eq(productId)
                        .and(product.isDeleted.eq(false))
                )
                .orderBy(productImage.seq.asc())
                .fetch();
    }

    @Override
    public List<ProductOptionResponseDto> findProductOptionDtoById(Long productId) {
        //return null;
        return queryFactory.select(
                    new QProductOptionResponseDto(productOption.id,productOption.name,
                            productOption.price,productOption.discountRate, productOption.isInputOption)
                )
                .from(productOption)
                .where(
                        productOption.product.id.eq(productId)
                        .and(productOption.isAvailable.eq(true))
                        .and(productOption.isHidden.eq(false))
                        .and(productOption.isDeleted.eq(false))
                )
                .orderBy(productOption.createdAt.desc())
                .fetch();
    }


    /**
     * 상품 조회의 모든 검색조건
     */

    // new 상품 조건
    private final BooleanExpression  isNewCondition = product.createdAt.after(LocalDateTime.now().minusDays(30));

    // 가격대별 조회 조건
    private BooleanExpression priceRangeCondition(PriceType priceType) {
        if (priceType == null) {
            return null; // 가격대별 조건이 없으면 조건 무시
        }
        // 가격으로 조회
        return product.price.between(priceType.getMinPrice(), priceType.getMaxPrice());
    }

    // 상 카테고리 검색 조건
    private BooleanExpression topCategoryCodeCondition(String topCode) {
        if (topCode == null || topCode.isEmpty()) {
            return null; // 상 카테고리 없으면 무시
        }
        // 상 카테고리 코드
        return categoryProductList.topCategoryCode.eq(topCode);
    }
    // 중 카테고리 검색 조건
    private BooleanExpression middleCategoryCodeCondition(List<String> middleCode) {
        if (middleCode == null || middleCode.isEmpty()) {
            return null; // 상 카테고리 없으면 무시
        }
        // 중 카테고리 코드
        return categoryProductList.middleCategoryCode.in(middleCode);
    }
    // 상품명으로 검색
    private BooleanExpression productNameCondition(String productName) {
        if (productName == null || productName.isEmpty()) {
            return null;
        }
        return product.name.like("%" + productName + "%");
    }

    // 기획전으로 검색 (여러개 선택 가능)
//    private BooleanExpression isExhibitionCondition(List<Long> exhibitionIds) {
//        return (exhibitionIds == null || exhibitionIds.isEmpty()) ? null :
//                exhibitionProduct.exhibition.id.in(exhibitionIds);
//    }
    // 상품 대표이미지 조건
    private final BooleanExpression  isMainCondition = productImage.seq.eq(0);
    // 정렬 조건
    private OrderSpecifier<?>[] getOrderSpecifier(OrderCondition orderCondition) {
        List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();
        // todo : 이상한 값 들어오면 예외처리
        if (orderCondition==null) { // 기본 정렬 (최신순)
            orderSpecifiers.add(new OrderSpecifier<>(Order.DESC, product.createdAt));
        }
        else {
            switch (orderCondition) {
                case NEWEST -> // 최신순 (내림차순)
                        orderSpecifiers.add(new OrderSpecifier<>(Order.DESC, product.createdAt));
                case DISCOUNT -> // 할인율 순 (내림차순)
                        orderSpecifiers.add(new OrderSpecifier<>(Order.DESC, product.discountRate));
                case HIGHEST_PRICE -> // 높은 가격순
                        orderSpecifiers.add(new OrderSpecifier<>(Order.DESC, product.price));
                case LOWEST_PRICE -> // 낮은 가격순
                        orderSpecifiers.add(new OrderSpecifier<>(Order.ASC, product.price));
                // todo : 추천순
            }
        }
        return orderSpecifiers.toArray(new OrderSpecifier[orderSpecifiers.size()]);
    }

}
