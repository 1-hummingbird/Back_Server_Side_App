package com.hummingbird.kr.starbuckslike.shipping.vo.in;

import com.hummingbird.kr.starbuckslike.shipping.dto.in.ShippingAddressDeleteRequestDTO;

public class ShippingAddressDeleteRequestVO {

    private Long shippingAddressId;

    public ShippingAddressDeleteRequestDTO toDTO(String memberUID) {
        return ShippingAddressDeleteRequestDTO.builder()
                .shippingAddressId(this.shippingAddressId)
                .memberUID(memberUID)
                .build();
    }

}
