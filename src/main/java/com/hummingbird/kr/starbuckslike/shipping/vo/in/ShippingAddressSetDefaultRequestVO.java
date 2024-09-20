package com.hummingbird.kr.starbuckslike.shipping.vo.in;

import com.hummingbird.kr.starbuckslike.shipping.dto.in.ShippingAddressSetDefaultRequestDTO;

public class ShippingAddressSetDefaultRequestVO {

    private Long shippingAddressId;

    public ShippingAddressSetDefaultRequestDTO toDTO(String memberUID) {
        return ShippingAddressSetDefaultRequestDTO.builder()
                .shippingAddressId(shippingAddressId)
                .memberUID(memberUID)
                .build();
    }
}
