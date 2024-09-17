package com.hummingbird.kr.starbuckslike.review.dto.in;

import com.hummingbird.kr.starbuckslike.review.domain.Review;
import com.hummingbird.kr.starbuckslike.review.domain.ReviewImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddReviewRequestDto {
    private String purchaseCode;

    private String nickname;
    private String memberUID;

    private Long productId;
    private Long optionId;

    private String content;
    private Integer star;

    List<AddReviewImageRequestDto> reviewImages;

    // 주문 엔티티 생성
    public Review toReview() {
        return Review.builder()
                .purchaseCode(purchaseCode)
                .nickname(nickname)
                .memberUID(memberUID)
                .productId(productId)
                .optionId(optionId)
                .content(content)
                .star(star)
                .isDelete(false)
                .build();
    }
    // 주문 상품 엔티티들 생성
    public List<ReviewImage> toReviewImage(Review review) {
        return reviewImages.stream()
                .map(imageDto -> ReviewImage.builder()
                        .review(review)
                        .seq(imageDto.getSeq())
                        .imageUrl(imageDto.getImageUrl())
                        .build())
                .collect(Collectors.toList());
    }

}
