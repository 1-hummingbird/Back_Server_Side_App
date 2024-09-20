package com.hummingbird.kr.starbuckslike.shipping.dto.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ShippingAddressDeleteRequestDTO {
    private Long shippingAddressID;
    private String memberUID;

    @Builder
    public ShippingAddressDeleteRequestDTO(Long shippingAddressID, String memberUID) {
        this.shippingAddressID = shippingAddressID;
        this.memberUID = memberUID;
    }
}
