package com.hummingbird.kr.starbuckslike.category.domain;

import com.hummingbird.kr.starbuckslike.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "middle_category", uniqueConstraints = {@UniqueConstraint(columnNames = {"category_code", "category_name"} )})
@Entity
public class MiddleCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "category_name" ,nullable = false, length = 30)
    private String categoryName;
    @Column(name = "category_description" ,nullable = true, length = 150)
    private String categoryDescription;
    @Column(name = "category_code" ,nullable = false, length = 20)
    private String categoryCode;

    @ManyToOne(fetch = FetchType.LAZY)
    private TopCategory topCategory;

}
