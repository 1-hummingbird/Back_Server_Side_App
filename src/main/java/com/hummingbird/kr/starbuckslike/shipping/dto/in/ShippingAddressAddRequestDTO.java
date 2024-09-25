package com.hummingbird.kr.starbuckslike.shipping.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import com.hummingbird.kr.starbuckslike.shipping.domain.ShippingAddress;

@Getter
@Builder
@AllArgsConstructor
public class ShippingAddressAddRequestDTO {
    private String memberUID;

    private String addressNickname;
    private String name;
    private String address;
    private String phone;
    private String memo;
    private Boolean willDefault;

    public ShippingAddress toEntity() {
        return ShippingAddress.builder()
                .memberUID(memberUID)
                .addressNickname(addressNickname)
                .name(name)
                .address(address)
                .Phone(phone)
                .memo(memo)
                .build();
    }
}
