package com.hummingbird.kr.starbuckslike.product.domain;

import com.hummingbird.kr.starbuckslike.category.domain.Category;
import com.hummingbird.kr.starbuckslike.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
/**
 * 상품 엔티티
 * @author 허정현
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name" , length = 200 , nullable = false)
    private String name;

    @Column(name = "price" , nullable = false)
    private Integer price = 0;

    @Column(name = "is_discounted")
    @ColumnDefault("false")
    private Boolean isDiscounted = false; // 할인 여부

    @Column(name = "discount_rate")
    private Double discountRate= 0.00; // 할인율

    @Column(name = "short_description", length = 200)
    private String shortDescription; // 짧은 상품설명 (텍스트)

    @Column(name = "full_description", columnDefinition = "LONGTEXT")
    private String fullDescription; // 전체 상품설명 (html)

    @Column(name = "status", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private SalesStatus status; // 판매 상태 : AVAILABLE, DISCONTINUED , HIDDEN


    @Column(name = "max_order", nullable = false)
    private Integer maxOrder; // 최대 주문 가능 수량

    @Column(name = "max_period", nullable = false)
    private Integer maxPeriod; // 최대 주문 가능 수량 정책 일

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category; // 상품의 카테고리
}