package com.hummingbird.kr.starbuckslike.member.dto.in;

import lombok.Getter;

@Getter
public class RefundRequestDTO {
    private String memberUID;
    private String purchasedProductId;

    public RefundRequestDTO(String memberUID, String purchasedProductId) {
        this.memberUID = memberUID;
        this.purchasedProductId = purchasedProductId;
    }
}
