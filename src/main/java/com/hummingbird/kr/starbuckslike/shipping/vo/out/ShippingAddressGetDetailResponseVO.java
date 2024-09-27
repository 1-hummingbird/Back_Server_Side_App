package com.hummingbird.kr.starbuckslike.shipping.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
public class ShippingAddressGetDetailResponseVO {

    private String addressNickname;
    private String name;
    private String address;
    private String Phone;
    private String memo;

}
