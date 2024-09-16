package com.hummingbird.kr.starbuckslike.member.vo.in;

import com.hummingbird.kr.starbuckslike.member.dto.in.RefundRequestDTO;
import lombok.Getter;

@Getter
public class RefundRequestVO {
    private String memberUID;
    private String purchasedProductId;

    public RefundRequestDTO toDTO() {
        return new RefundRequestDTO(memberUID, purchasedProductId);
    }
}
