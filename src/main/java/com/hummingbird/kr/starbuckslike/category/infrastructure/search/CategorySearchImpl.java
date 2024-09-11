package com.hummingbird.kr.starbuckslike.category.infrastructure.search;

import com.hummingbird.kr.starbuckslike.category.domain.QMiddleCategory;
import com.hummingbird.kr.starbuckslike.category.domain.QTopCategory;
import com.hummingbird.kr.starbuckslike.category.domain.TopCategory;
import com.hummingbird.kr.starbuckslike.category.dto.out.ChildCategoryResponseDto;
import com.hummingbird.kr.starbuckslike.category.dto.out.MainCategoryResponseDto;
import com.hummingbird.kr.starbuckslike.category.dto.out.QChildCategoryResponseDto;
import com.hummingbird.kr.starbuckslike.category.dto.out.QMainCategoryResponseDto;
import com.hummingbird.kr.starbuckslike.category.infrastructure.TopCategoryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.hummingbird.kr.starbuckslike.category.domain.QMiddleCategory.middleCategory;
import static com.hummingbird.kr.starbuckslike.category.domain.QTopCategory.topCategory;

@Repository
@RequiredArgsConstructor
public class CategorySearchImpl implements  CategorySearch{

    private final JPAQueryFactory queryFactory;
    private final TopCategoryRepository topCategoryRepository;

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

    @Override
    public List<ChildCategoryResponseDto> findChildCategoriesByTopCategory(String categoryCode) {
        Integer parent_id = topCategoryRepository.findByCategoryCode(categoryCode).orElseThrow().getId();
        return queryFactory
                .select(new QChildCategoryResponseDto(middleCategory.categoryCode,middleCategory.categoryName))
                .from(middleCategory)
                .where(middleCategory.topCategory.id.eq(parent_id))
                .fetch();
    }


}
