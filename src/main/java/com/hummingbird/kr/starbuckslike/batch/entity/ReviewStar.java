package com.hummingbird.kr.starbuckslike.batch.entity;

import com.hummingbird.kr.starbuckslike.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Table(name = "review_star", uniqueConstraints = {
        @UniqueConstraint(columnNames = "productId") // productId에 대한 유니크 제약 조건 설정
})
public class ReviewStar extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Comment("상품 ID")
    @Column(nullable = false, unique = true) // productId에 대해 UNIQUE 제약 조건 설정
    private Long productId;

    @Comment("총 리뷰 개수")
    @Column(nullable = false)
    private Long reviewCount;

    @Comment("평균 별점")
    @Column(nullable = false)
    private Double averageStar;

    @Comment("포토 리뷰 수")
    @Column(nullable = false)
    private Long photoReviewCount;

    @Comment("총 별점의 합")
    @Column(nullable = false)
    private Long totalStar; // 평균을 계산하기 위해 총 별점 합계를 저장

    @Builder
    public ReviewStar(Long productId, Long reviewCount, Double averageStar, Long photoReviewCount, Long totalStar) {
        this.productId = productId;
        this.reviewCount = reviewCount;
        this.averageStar = averageStar;
        this.photoReviewCount = photoReviewCount;
        this.totalStar = totalStar;
    }

}
