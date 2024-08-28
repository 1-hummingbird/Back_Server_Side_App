package com.hummingbird.kr.starbuckslike.temp.repository.search;

import com.hummingbird.kr.starbuckslike.temp.domain.Product;
import com.hummingbird.kr.starbuckslike.temp.dto.ProductListDto;
import com.hummingbird.kr.starbuckslike.temp.dto.QProductListDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
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
        // 상품이 최근 30일 안에 생성되었으면 true , 아니면 false
        BooleanExpression isNewCondition = product.createdAt.after(LocalDateTime.now().minusDays(30));

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
}
