package com.hummingbird.kr.starbuckslike.exhibition.infrastructure;


import com.hummingbird.kr.starbuckslike.exhibition.domain.ExhibitionProduct;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 기획전 <-> 상품 중간 테이블
 * @author 허정현
 */
public interface ExhibitionProductRepository extends JpaRepository<ExhibitionProduct,Long> {
}
