package com.hummingbird.kr.starbuckslike.review.vo.in;

import lombok.Getter;
import java.util.List;

@Getter
public class AddReviewRequestVo {
    private String purchaseCode;

    private Long purchaseProductId; // 주문상품 Id
    private Long productId;
    private Long optionId;

    private String content;
    private Integer star;

    private List<AddReviewImageRequestVo> reviewImages;
}
