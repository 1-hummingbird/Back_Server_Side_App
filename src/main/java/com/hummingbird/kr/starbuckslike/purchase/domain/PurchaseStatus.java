package com.hummingbird.kr.starbuckslike.purchase.domain;

import lombok.Getter;

@Getter
public enum PurchaseStatus {
    PENDING("배송 준비"),
    SHIPPED("배송중"),
    DELIVERED("배송 완료"),
    CONFIRMED("구매 확정");

    // 상태 값 반환
    private final String description;

    PurchaseStatus(String description) {
        this.description = description;
    }

}
