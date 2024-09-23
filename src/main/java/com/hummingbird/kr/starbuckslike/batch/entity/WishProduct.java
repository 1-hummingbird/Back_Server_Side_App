package com.hummingbird.kr.starbuckslike.batch.entity;

import com.hummingbird.kr.starbuckslike.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "wish_product" , uniqueConstraints = {
        @UniqueConstraint(columnNames = "productId") // productId UNIQUE 제약 조건 설정
})
public class WishProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Comment("상품 ID")
    @Column(nullable = false)
    private Long productId;

    @Comment("좋아요 수")
    @Column(nullable = false)
    private Long wishCount;

    @Builder
    public WishProduct(Long productId, Long wishCount) {
        this.productId = productId;
        this.wishCount = wishCount;
    }

    // 좋아요 수 업데이트
    public void updateWishCount(Long wishCount) {
        this.wishCount = wishCount;
    }
}