package com.hummingbird.kr.starbuckslike.category.infrastructure.search;

import com.hummingbird.kr.starbuckslike.category.domain.QTopCategory;
import com.hummingbird.kr.starbuckslike.category.dto.out.MainCategoryResponseDto;
import com.hummingbird.kr.starbuckslike.category.dto.out.QMainCategoryResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.hummingbird.kr.starbuckslike.category.domain.QTopCategory.topCategory;

@Repository
@RequiredArgsConstructor
public class CategorySearchImpl implements  CategorySearch{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MainCategoryResponseDto> findMainCategoryResponseDto() {
        return queryFactory
                .select(new QMainCategoryResponseDto(topCategory.categoryCode,
                                                     topCategory.categoryName,
                                                     topCategory.imageUrl))
                .from(topCategory)
                .orderBy(topCategory.categoryName.asc())
                .fetch();

    }
}
