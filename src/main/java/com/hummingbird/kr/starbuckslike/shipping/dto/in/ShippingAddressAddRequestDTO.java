package com.hummingbird.kr.starbuckslike.shipping.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

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
}
