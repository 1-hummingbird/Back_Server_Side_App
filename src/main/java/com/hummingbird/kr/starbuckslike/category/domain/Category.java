package com.hummingbird.kr.starbuckslike.category.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * 카테고리 엔티티
 * @author 허정현
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "category_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent")
    private Category parent; // 부모 카테고리

    @Column(name = "depth")
    private Integer depth; // 깊이 0은 루트 카테고리

    @Column(name = "path",nullable = false)
    @Setter
    private String path; // 카테고리 경로

    @Column(name = "category_image", nullable = true)
    private String image; // 카테고리 이미지 경로
}