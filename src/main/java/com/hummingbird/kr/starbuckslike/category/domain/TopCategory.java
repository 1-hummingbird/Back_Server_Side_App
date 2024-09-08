package com.hummingbird.kr.starbuckslike.category.domain;

import com.hummingbird.kr.starbuckslike.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "top_category", uniqueConstraints = {@UniqueConstraint(columnNames = "categoryCode, categoryName")})
public class TopCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "category_name" ,nullable = false, length = 30)
    private String categoryName;
    @Column(name = "category_name" ,nullable = true, length = 150)
    private String categoryDescription;
    @Column(name = "category_code" ,nullable = false, length = 20)
    private String categoryCode;

    @Column(name = "image_url" ,nullable = true)
    private String imageUrl;
}