package com.hummingbird.kr.starbuckslike.product.domain;

import com.hummingbird.kr.starbuckslike.common.entity.BaseEntity;
import com.querydsl.core.annotations.QueryProjection;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
/**
 * 상품 엔티티
 * @author 허정현
 */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_name" , length = 200 , nullable = false)
    private String name;

    @Column(name = "price" , nullable = false)
    private Integer price;

    @Column(name = "is_discounted")
    @ColumnDefault("false")
    private Boolean isDiscounted; // 할인 여부

    @Column(name = "discount_rate")
    private Float discountRate; // 할인율

    @Column(name = "short_description", length = 200)
    private String shortDescription; // 짧은 상품설명 (텍스트)

    @Column(name = "full_description", columnDefinition = "LONGTEXT")
    private String fullDescription; // 전체 상품설명 (html)

    @Column(nullable = false , name = "is_available")
    private Boolean isAvailable; // 판매 여부

    @Column(nullable = false , name = "is_hidden")
    private Boolean isHidden; // 숨김 여부

    @Column(name = "is_deleted", nullable = false)
    @ColumnDefault("false")
    private Boolean isDeleted; // 삭제 여부

//    @Column(name = "max_order", nullable = false)
//    private Integer maxOrder; // 최대 주문 가능 수량
//
//    @Column(name = "max_period", nullable = false)
//    private Integer maxPeriod; // 최대 주문 가능 수량 정책 일


    @Builder

    public Product(String name, Integer price, Boolean isDiscounted, Float discountRate, String shortDescription,
                   String fullDescription, Boolean isAvailable, Boolean isHidden, Boolean isDeleted) {
        this.name = name;
        this.price = price;
        this.isDiscounted = isDiscounted;
        this.discountRate = discountRate;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.isAvailable = isAvailable;
        this.isHidden = isHidden;
        this.isDeleted = isDeleted;
    }
}