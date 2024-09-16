package com.hummingbird.kr.starbuckslike.member.vo.out;

import java.util.List;

import lombok.Getter;

@Getter
public class CanReviewResponseVO {
    List<Long> purchasedProductIds;

    public CanReviewResponseVO(List<Long> purchasedProductIds) {
        this.purchasedProductIds = purchasedProductIds;
    }
}
