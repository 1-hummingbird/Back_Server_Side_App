package com.hummingbird.kr.starbuckslike.product.domain;

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
@Table(name = "wish")
public class Wish extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Comment("회원 uuid")
    @Column(nullable = false, length = 50)
    private String memberUid;

    @Comment("상품 id")
    @Column(nullable = false, length = 50)
    private Long productId;

    @Comment("위시리스트 상태")
    @Column(nullable = false)
    private Boolean isWished;  // 최초 insert 시 true , 이후 토글처럼 true, false 업데이트로 관리

    public void toggleWishStatus() {
        isWished = !this.isWished;
    }

    @Builder
    public Wish(String memberUid, Long productId, Boolean isWished) {
        this.memberUid = memberUid;
        this.productId = productId;
        this.isWished = isWished;
    }
}
