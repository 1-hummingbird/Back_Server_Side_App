package com.hummingbird.kr.starbuckslike.delivery.vo;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryResponseVo {

    private Long id;
    private String addressnickname;
//    private name;
//    private address;
    private String mainphone;
    private String secphone;
    private String userUuid;
    private String memo;
    private Boolean isBasic;
}
