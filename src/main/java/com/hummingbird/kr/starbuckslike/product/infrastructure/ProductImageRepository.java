package com.hummingbird.kr.starbuckslike.product.infrastructure;

import com.hummingbird.kr.starbuckslike.product.domain.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage,Long> {
}
