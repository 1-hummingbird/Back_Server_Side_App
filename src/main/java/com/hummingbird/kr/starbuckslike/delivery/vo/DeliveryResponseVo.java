package com.hummingbird.kr.starbuckslike.delivery.vo;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryResponseVo {

    private Long id;
    private String alias;
//    private name;
//    private address;
    private String phone;
    private String userUuid;
    private String memo;
    private Boolean isBasic;
}
