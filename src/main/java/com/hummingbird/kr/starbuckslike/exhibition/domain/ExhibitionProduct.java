package com.hummingbird.kr.starbuckslike.exhibition.domain;

import com.hummingbird.kr.starbuckslike.product.domain.Product;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

/**
 * 기획전 <-> 상품 중간 테이블
 * @author 허정현
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "exhibition_product")
public class ExhibitionProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "exhibition_id")
    private Exhibition exhibition;

    @Column(name = "product_id")
    private Long productId;

    @Builder
    public ExhibitionProduct(Exhibition exhibition, Long productId) {
        this.exhibition = exhibition;
        this.productId = productId;
    }
}
