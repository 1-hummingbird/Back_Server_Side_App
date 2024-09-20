package com.hummingbird.kr.starbuckslike.shipping.dto.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ShippingAddressDeleteRequestDTO {
    private Long shippingAddressId;
    private String memberUID;

    @Builder
    public ShippingAddressDeleteRequestDTO(Long shippingAddressId, String memberUID) {
        this.shippingAddressId = shippingAddressId;
        this.memberUID = memberUID;
    }
}
