package com.hummingbird.kr.starbuckslike.shipping.vo.in;

import com.hummingbird.kr.starbuckslike.shipping.dto.in.ShippingAddressSetDefaultRequestDTO;

import lombok.Getter;

@Getter
public class ShippingAddressSetDefaultRequestVO {

    private Long shippingAddressID;

    public ShippingAddressSetDefaultRequestDTO toDTO(String memberUID) {
        return ShippingAddressSetDefaultRequestDTO.builder()
                .shippingAddressID(shippingAddressID)
                .memberUID(memberUID)
                .build();
    }
}
