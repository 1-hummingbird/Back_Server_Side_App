package com.hummingbird.kr.starbuckslike.category.dto.in;

import com.hummingbird.kr.starbuckslike.category.domain.CategoryProductList;
import com.hummingbird.kr.starbuckslike.product.domain.Product;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryProductListRequestDto {
    private String topCategoryCode;
    private String middleCategoryCode;
    private String bottomCategoryCode;
    private Long productId;

    public CategoryProductList toEntity(Product product) {
        return CategoryProductList.builder()
                .topCategoryCode(topCategoryCode)
                .middleCategoryCode(middleCategoryCode)
                .bottomCategoryCode(bottomCategoryCode)
                .product(product)
                .build();
    }
}
