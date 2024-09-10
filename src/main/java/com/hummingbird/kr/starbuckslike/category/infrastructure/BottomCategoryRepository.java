package com.hummingbird.kr.starbuckslike.category.infrastructure;

import com.hummingbird.kr.starbuckslike.category.domain.BottomCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BottomCategoryRepository extends JpaRepository<BottomCategory, Integer> {

    List<BottomCategory> findByMiddleCategoryCategoryCode(String middleCategoryCode);
    Optional<BottomCategory> findByCategoryCode(String categoryCode);
    boolean existsByCategoryCode(String categoryCode);
    boolean existsByCategoryName(String categoryName);
}
