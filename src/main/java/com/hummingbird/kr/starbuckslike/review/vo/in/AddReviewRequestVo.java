package com.hummingbird.kr.starbuckslike.review.vo.in;

import com.hummingbird.kr.starbuckslike.review.dto.in.AddReviewImageRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
