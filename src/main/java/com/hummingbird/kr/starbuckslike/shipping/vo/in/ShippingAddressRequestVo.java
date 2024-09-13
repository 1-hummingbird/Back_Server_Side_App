package com.hummingbird.kr.starbuckslike.shipping.vo.in;

import lombok.Getter;

@Getter
public class ShippingAddressRequestVo {

    private String addressNickname;
    private String name;
    private String address;
    private String primaryPhone;
    private String secondaryPhone;
    private String memo;
    private Boolean defaultAddress;

}
