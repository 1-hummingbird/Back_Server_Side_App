package com.hummingbird.kr.starbuckslike.delivery.vo;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter

public class DeliveryRequestVo {
    private Long id;
    private String alias;
//    private name;
//    private address;
    private String phone;
    private String userUuid;
    private String memo;
    private Boolean isBasic;

}