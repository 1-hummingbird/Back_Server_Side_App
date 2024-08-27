package com.hummingbird.kr.starbuckslike.temp.repository.search;

import com.hummingbird.kr.starbuckslike.temp.domain.Product;
import com.hummingbird.kr.starbuckslike.temp.domain.QCategory;
import com.hummingbird.kr.starbuckslike.temp.domain.QProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.hummingbird.kr.starbuckslike.temp.domain.QCategory.category;
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
                .where(category.path.startsWith(path))
                .fetch();
    }
}
