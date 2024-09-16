package com.hummingbird.kr.starbuckslike.review.domain;

import com.hummingbird.kr.starbuckslike.common.entity.BaseEntity;
import com.hummingbird.kr.starbuckslike.product.domain.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = LAZY) //
    @JoinColumn(name="review_id") //
    private Review review;

    @Column(name = "review_image_url")
    private String imageUrl;

    @Column(name = "seq")
    private Integer seq; // 이미지 순서 0,1,2 ... 0이 대표이미지

    @Builder
    public ReviewImage(Review review, String imageUrl, Integer seq) {
        this.review = review;
        this.imageUrl = imageUrl;
        this.seq = seq;
    }
}
