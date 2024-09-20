package com.hummingbird.kr.starbuckslike.review.domain;

import com.hummingbird.kr.starbuckslike.common.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@ToString
@Table(name = "review")
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Comment("주문 코드")
    @Column(nullable = false, length = 30)
    private String purchaseCode;

    @Comment("회원 닉네임")
    @Column(length = 40)
    private String nickname;

    @Comment("회원 UUID")
    @Column(nullable = false, length = 80)
    private String memberUID;

    @Comment("상품 id")
    @Column(nullable = false)
    private Long productId;

    @Comment("상품옵션 id")
    @Column(nullable = false)
    private Long optionId;

    @Comment("리뷰 내용")
    @Column(nullable = false, length = 500)
    private String content;

    @Comment("별점")
    @Column(nullable = false, columnDefinition = "TINYINT CHECK (star BETWEEN 1 AND 5)")
    @Min(1) @Max(5)
    private Integer star;

    @Comment("리뷰 댓글 개수")
    @Column(nullable = false)
    private Integer commentCount;

    @Comment("삭제 여부")
    @Column(nullable = false)
    private Boolean isDeleted;

    // 댓글 수 증가
    public void increaseCommentCount() {
        this.commentCount += 1;
    }
    // 댓글 수 감소
    public void decreaseCommentCount() {
        this.commentCount -= 1;
    }

    @Builder
    public Review(String purchaseCode, String nickname, String memberUID, Long productId, Long optionId,
                  String content, Integer star, Integer commentCount, Boolean isDeleted) {
        this.purchaseCode = purchaseCode;
        this.nickname = nickname;
        this.memberUID = memberUID;
        this.productId = productId;
        this.optionId = optionId;
        this.content = content;
        this.star = star;
        this.commentCount = commentCount;
        this.isDeleted = isDeleted;
    }
}
