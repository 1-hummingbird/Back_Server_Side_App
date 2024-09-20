package com.hummingbird.kr.starbuckslike.shipping.vo.in;

import com.hummingbird.kr.starbuckslike.shipping.dto.in.ShippingAddressDeleteRequestDTO;

import lombok.Getter;

@Getter
public class ShippingAddressDeleteRequestVO {

    private Long shippingAddressID;

    public ShippingAddressDeleteRequestDTO toDTO(String memberUID) {
        return ShippingAddressDeleteRequestDTO.builder()
                .shippingAddressID(this.shippingAddressID)
                .memberUID(memberUID)
                .build();
    }

}
