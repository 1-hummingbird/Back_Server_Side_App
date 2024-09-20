package com.hummingbird.kr.starbuckslike.review.vo.in;

import lombok.Getter;
import java.util.List;

@Getter
public class AddReviewRequestVo {
    private String purchaseCode;

    private String nickname;
    private String memberUID;

    private Long productId;
    private Long optionId;

    private String content;
    private Integer star;

    private List<AddReviewImageRequestVo> reviewImages;
}
