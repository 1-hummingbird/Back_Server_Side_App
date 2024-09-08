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
@Table(name = "bottom_category", uniqueConstraints = {@UniqueConstraint(columnNames = "categoryCode, categoryName")})
@Entity
public class BottomCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "category_name" ,nullable = false, length = 30)
    private String categoryName;
    @Column(name = "category_name" ,nullable = true, length = 150)
    private String categoryDescription;
    @Column(name = "category_code" ,nullable = false, length = 20)
    private String categoryCode;

    @ManyToOne(fetch = FetchType.LAZY)
    private MiddleCategory middleCategory;

}