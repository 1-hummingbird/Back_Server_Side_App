package com.hummingbird.kr.starbuckslike.delivery.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class DeliveryDto {


    private Long id;
    private String alias;
    private String name;
    private String address;
    private int phone;
    private String userid;
    //11231
    //   private String basic_flag;
}
