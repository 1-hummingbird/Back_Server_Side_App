package com.hummingbird.kr.starbuckslike.shipping.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ShippingAddressSetDefaultRequestDTO {

    private Long shippingAddressID;
    private String memberUID;
}
