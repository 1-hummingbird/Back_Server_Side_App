package com.hummingbird.kr.starbuckslike.review.domain;

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
@Table(name = "review_comment")
public class ReviewComment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Comment("리뷰 id")
    @Column(nullable = false)
    private Long reviewId;

    @Comment("회원 닉네임")
    @Column(length = 40)
    private String nickname;

    @Comment("회원 UUID")
    @Column(nullable = false, length = 80)
    private String memberUID;

    @Comment("댓글 내용")
    @Column(nullable = false, length = 500)
    private String content;

    @Builder
    public ReviewComment(Long reviewId, String nickname, String memberUID, String content) {
        this.reviewId = reviewId;
        this.nickname = nickname;
        this.memberUID = memberUID;
        this.content = content;
    }
}
