package com.hummingbird.kr.starbuckslike.temp.repository.search;

import com.hummingbird.kr.starbuckslike.temp.domain.Product;
import com.hummingbird.kr.starbuckslike.temp.dto.ProductListDto;
import com.hummingbird.kr.starbuckslike.temp.dto.QProductListDto;
import com.hummingbird.kr.starbuckslike.temp.repository.condition.PriceType;
import com.hummingbird.kr.starbuckslike.temp.repository.condition.ProductCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.hummingbird.kr.starbuckslike.temp.domain.QCategory.category;
import static com.hummingbird.kr.starbuckslike.temp.domain.QExhibitionProduct.exhibitionProduct;
import static com.hummingbird.kr.starbuckslike.temp.domain.QProduct.product;
/**
 * 상품 조회  (querydsl 등 조회 쿼리, JpaRepository와 따로 두었음)
 * 지금은 엔티티로 받는데 나중에 DTO나 VO로 받는 부분 추가해야 함
 * @author 허정현
 */
@Repository
public class ProductSearchImpl implements ProductSearch{
    private final JPAQueryFactory queryFactory;
    public ProductSearchImpl(EntityManager em) {
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
    public List<ProductListDto> findProductListById(Long exhibitionId) {
        return queryFactory
                .selectDistinct(
                        new QProductListDto(product.id, product.name, product.price,
                                new CaseBuilder() // sql case문
                                        .when(isNewCondition).then(true)
                                        .otherwise(false)
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
                                        .otherwise(false)
                        )
                )
                .from(product)
                .leftJoin(category).on(product.category.id.eq(category.id))
                .leftJoin(exhibitionProduct).on(product.id.eq(exhibitionProduct.id))
                .where(
                        priceRangeCondition(productCondition.getPriceType()), // 가격 필터링
                        categoryPathStartsWith(productCondition.getPath()),  // 상품 카테고리 필터링
                        isExhibitionCondition(productCondition.getExhibitionId()) // 기획전 상품 필터링
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
                        isExhibitionCondition(productCondition.getExhibitionId()) // 기획전 상품 필터링
                );

        return PageableExecutionUtils.getPage(fetch, pageable, countQuery::fetchOne) ;
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

    // todo : 기획전으로 검색
    private BooleanExpression isExhibitionCondition(Long exhibitionId) {
        return (exhibitionId == null) ? null : exhibitionProduct.exhibition.id.eq(exhibitionId);
    }

}
