package com.hummingbird.kr.starbuckslike.category.domain;
import com.hummingbird.kr.starbuckslike.common.entity.BaseEntity;
import com.hummingbird.kr.starbuckslike.product.domain.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CategoryProductList extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topCategoryCode;
    private String middleCategoryCode;
    private String bottomCategoryCode;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

}