package com.hummingbird.kr.starbuckslike.product.infrastructure;

import com.hummingbird.kr.starbuckslike.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}