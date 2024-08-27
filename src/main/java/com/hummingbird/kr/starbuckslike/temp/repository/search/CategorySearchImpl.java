package com.hummingbird.kr.starbuckslike.temp.repository.search;

import com.hummingbird.kr.starbuckslike.temp.domain.Category;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.hummingbird.kr.starbuckslike.temp.domain.QCategory.category;
/**
 * 카테고리 조회  (querydsl 등 조회 쿼리, JpaRepository와 따로 두었음)
 * 지금은 엔티티로 받는데 나중에 DTO나 VO로 받는 부분 추가해야 함
 * @author 허정현
 */
@Repository
public class CategorySearchImpl implements CategorySearch {

    private final JPAQueryFactory queryFactory;
    public CategorySearchImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Category> findCategoryByDepth(Integer depth) {
        return queryFactory
                .select(category)
                .from(category)
                .where(category.depth.eq(depth))
                .orderBy(category.id.asc())
                .fetch();
    }
}
