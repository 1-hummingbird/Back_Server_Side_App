package com.hummingbird.kr.starbuckslike.delivery.vo.in;

import lombok.Getter;
import lombok.ToString;

@ToString
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
