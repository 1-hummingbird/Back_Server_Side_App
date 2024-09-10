package com.hummingbird.kr.starbuckslike.product.domain;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

/**
 * 상품 엔티티
 * @author 허정현
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product_image")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = LAZY) // 상품이미지 <-> 상품  다대일 관계
    @JoinColumn(name="product_id") //
    private Product product;

    @Column(name = "product_image_url")
    private String imageUrl;

    @Column(name = "seq")
    private Integer seq; // 이미지 순서 0,1,2 ... 0이 대표이미지


}
