package com.hummingbird.kr.starbuckslike.batch.repository;

import com.hummingbird.kr.starbuckslike.batch.entity.WishProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishProductRepository extends JpaRepository<WishProduct, Long> {
    //
    Optional<WishProduct> findByProductId(Long productId);

}