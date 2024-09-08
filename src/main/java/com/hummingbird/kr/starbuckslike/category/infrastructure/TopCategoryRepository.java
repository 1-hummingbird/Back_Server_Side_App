package com.hummingbird.kr.starbuckslike.category.infrastructure;


import com.hummingbird.kr.starbuckslike.category.domain.TopCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopCategoryRepository extends JpaRepository<TopCategory, Integer> {
    Optional<TopCategory> findByCategoryName(String categoryName);
    Optional<TopCategory> findByCategoryCode(String categoryCode);
    Optional<TopCategory> findFirstByOrderByIdDesc();
    boolean existsByCategoryName(String categoryName);
    boolean existsByCategoryCode(String categoryCode);
}
