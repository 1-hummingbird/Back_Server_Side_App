package com.hummingbird.kr.starbuckslike.category.infrastructure;

import com.hummingbird.kr.starbuckslike.category.domain.CategoryProductList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryProductListRepository extends JpaRepository<CategoryProductList, Long> {
//    List<ProductCategoryList> findByTopCategoryId(Integer topCategoryId);
//    List<ProductCategoryList> findByMiddleCategoryId(Integer middleCategoryId);
//    List<ProductCategoryList> findByBottomCategoryId(Integer bottomCategoryId);
//    List<ProductCategoryList> findByProductId(Long productId);
}
