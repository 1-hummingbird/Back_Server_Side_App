package com.hummingbird.kr.starbuckslike.category.infrastructure;

import com.hummingbird.kr.starbuckslike.category.domain.MiddleCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MiddleCategoryRepository extends JpaRepository<MiddleCategory, Integer> {

    List<MiddleCategory> findByTopCategoryCategoryCode(String topCategoryCode);
    List<MiddleCategory> findByTopCategoryCategoryName(String topCategoryName);
    Optional<MiddleCategory> findByCategoryCode(String categoryCode);
    boolean existsByCategoryCode(String categoryCode);
    boolean existsByCategoryName(String categoryName);
}
