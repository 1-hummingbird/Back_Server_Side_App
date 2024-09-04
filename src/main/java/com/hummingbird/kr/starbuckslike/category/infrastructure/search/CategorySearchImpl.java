package com.hummingbird.kr.starbuckslike.category.infrastructure.search;

import com.hummingbird.kr.starbuckslike.category.dto.CategoryListDto;
import com.hummingbird.kr.starbuckslike.category.dto.MainCategoryListDto;
import com.hummingbird.kr.starbuckslike.category.dto.QCategoryListDto;
import com.hummingbird.kr.starbuckslike.category.dto.QMainCategoryListDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.hummingbird.kr.starbuckslike.category.domain.QCategory.category;

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
    public List<CategoryListDto> findCategoryByDepth(Integer depth) {
        return queryFactory
                .select(new QCategoryListDto(category.id, category.name, category.path))
                .from(category)
                .where(category.depth.eq(depth))
                .orderBy(category.id.asc())
                .fetch();
    }

    @Override
    public List<MainCategoryListDto> findAllRootCategory() {
        return queryFactory
                .select(new QMainCategoryListDto(category.id, category.name, category.path, category.image))
                .from(category)
                .where(
                        rootCondition() // 최상위 부모 카테고리만 검색
                )
                .orderBy(category.id.asc())
                .fetch();
    }
    /**
     * 카테고리 조회의 모든 검색조건
     */
    private final BooleanExpression rootCondition() {
        return category.depth.eq(0).and(category.parent.isNull());
    }
}
