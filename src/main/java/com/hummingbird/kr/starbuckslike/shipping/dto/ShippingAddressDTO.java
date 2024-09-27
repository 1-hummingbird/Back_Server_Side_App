package com.hummingbird.kr.starbuckslike.shipping.dto;

import com.hummingbird.kr.starbuckslike.shipping.domain.ShippingAddress;

import lombok.Getter;

@Getter
public class ShippingAddressDTO {

    private final Long id;

    private final String addressNickname;

    private final String name;

    private final String address;

    private final String phone;

    private final String memo;

    public ShippingAddressDTO(ShippingAddress shippingAddress) {
        this.id = shippingAddress.getId();
        this.addressNickname = shippingAddress.getAddressNickname();
        this.name = shippingAddress.getName();
        this.address = shippingAddress.getAddress();
        this.phone = shippingAddress.getPhone();
        this.memo = shippingAddress.getMemo();
    }
}
