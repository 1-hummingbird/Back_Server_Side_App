package com.hummingbird.kr.starbuckslike.temp.domain;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

/**
 * 기획전 <-> 상품 중간 테이블
 * @author 허정현
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "exhibition_product")
public class ExhibitionProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exhibition_product_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "exhibition_id")
    private Exhibition exhibition;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;


}