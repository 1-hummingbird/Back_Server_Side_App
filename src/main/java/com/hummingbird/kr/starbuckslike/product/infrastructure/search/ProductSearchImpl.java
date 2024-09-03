package com.hummingbird.kr.starbuckslike.product.infrastructure.search;

import com.hummingbird.kr.starbuckslike.product.domain.Product;
import com.hummingbird.kr.starbuckslike.product.domain.ProductImage;
import com.hummingbird.kr.starbuckslike.product.domain.QProduct;
import com.hummingbird.kr.starbuckslike.product.domain.QProductImage;
import com.hummingbird.kr.starbuckslike.product.dto.*;
import com.hummingbird.kr.starbuckslike.product.infrastructure.ProductImageRepository;
import com.hummingbird.kr.starbuckslike.product.infrastructure.ProductRepository;
import com.hummingbird.kr.starbuckslike.product.infrastructure.condition.OrderCondition;
import com.hummingbird.kr.starbuckslike.product.infrastructure.condition.PriceType;
import com.hummingbird.kr.starbuckslike.product.infrastructure.condition.ProductCondition;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.hummingbird.kr.starbuckslike.category.domain.QCategory.category;
import static com.hummingbird.kr.starbuckslike.exhibition.domain.QExhibitionProduct.exhibitionProduct;
import static com.hummingbird.kr.starbuckslike.product.domain.QProduct.product;
import static com.hummingbird.kr.starbuckslike.product.domain.QProductImage.productImage;

/**
 * 상품 조회  (querydsl 등 조회 쿼리, JpaRepository와 따로 두었음)
 * 지금은 엔티티로 받는데 나중에 DTO나 VO로 받는 부분 추가해야 함
 * @author 허정현
 */
@Repository
public class ProductSearchImpl implements ProductSearch {

    private final JPAQueryFactory queryFactory;
    public ProductSearchImpl(EntityManager em)
    {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Product> findProductsByPath(String path) {
        return queryFactory
                .selectFrom(product)
                .join(product.category, category)
                .where(category.path.startsWith(path)) // like path %
                .fetch();
    }

    @Override
    public List<ProductListDto> findProductListDtoByExhibitionId(Long exhibitionId) {
        return queryFactory
                .selectDistinct(
                        new QProductListDto(product.id, product.name, product.price,
                                new CaseBuilder() // sql case문
                                        .when(isNewCondition).then(true)
                                        .otherwise(false),
                                product.isDiscounted, product.discountRate
                        )
                )
                .from(product)
                // 상품 id 와 기획전상품(중간 태이블)의 id 조인
                .join(exhibitionProduct).on(product.id.eq(exhibitionProduct.product.id))
                // 특정 기획전 상품에 (exhibitionId) 에 해당하는 상품만 조회
                .where(exhibitionProduct.exhibition.id.eq(exhibitionId))
                .fetch();
    }

    @Override
    public Page<ProductListDto> searchProductListPageV1(ProductCondition productCondition, Pageable pageable) {
        List<ProductListDto> fetch = queryFactory
                .select(
                        new QProductListDto(product.id, product.name, product.price,
                                new CaseBuilder() // sql case문
                                        .when(isNewCondition).then(true)
                                        .otherwise(false),
                                product.isDiscounted, product.discountRate
                        )
                )
                .from(product)
                .leftJoin(category).on(product.category.id.eq(category.id))
                .leftJoin(exhibitionProduct).on(product.id.eq(exhibitionProduct.id))
                .where(
                        priceRangeCondition(productCondition.getPriceType()), // 가격 필터링
                        categoryPathStartsWith(productCondition.getPath()),  // 상품 카테고리 필터링
                        isChildCategoryCondition(productCondition.getChildCategoryIds()) , // 자식 카테고리 필터링
                        isExhibitionCondition(productCondition.getExhibitionIds()) // 여러 기획전 상품 필터링
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
                .leftJoin(category).on(product.category.id.eq(category.id))
                .leftJoin(exhibitionProduct).on(product.id.eq(exhibitionProduct.id))
                .where(
                        priceRangeCondition(productCondition.getPriceType()), // 가격 필터링
                        categoryPathStartsWith(productCondition.getPath()),  // 상품 카테고리 필터링
                        isChildCategoryCondition(productCondition.getChildCategoryIds()) , // 자식 카테고리 필터링
                        isExhibitionCondition(productCondition.getExhibitionIds()) // 여러 기획전 상품 필터링
                );

        return PageableExecutionUtils.getPage(fetch, pageable, countQuery::fetchOne) ;
    }

    @Override
    public ProductDetailDto findProductDetailDtoById(Long productId) {
        return queryFactory.select(new QProductDetailDto(product.fullDescription))
                .from(product)
                .where(product.id.eq(productId))
                .fetchOne();

    }

    @Override
    public List<ProductImageDto> findProductImageDtoById(Long productId) {
        return queryFactory.select(new QProductImageDto(productImage.url,
                                        new CaseBuilder()
                                                .when(isMainCondition).then(true)
                                                .otherwise(false)
                                        )
                )
                .from(productImage)
                .where(productImage.product.id.eq(productId))
                .orderBy(productImage.seq.asc())
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

    // 카테고리 path 검색
    private BooleanExpression categoryPathStartsWith(String path) {
        return (path == null || path.isEmpty()) ?
                null : category.path.startsWith(path);
    }

    // 자식 카테고리 검색 (여러개 선택 가능)
    private BooleanExpression isChildCategoryCondition(List<Long> childCategoryIds) {
        return (childCategoryIds == null || childCategoryIds.isEmpty()) ? null :
                category.id.in(childCategoryIds);
    }

    // 기획전으로 검색 (여러개 선택 가능)
    private BooleanExpression isExhibitionCondition(List<Long> exhibitionIds) {
        return (exhibitionIds == null || exhibitionIds.isEmpty()) ? null :
                exhibitionProduct.exhibition.id.in(exhibitionIds);
    }
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
