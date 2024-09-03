package com.hummingbird.kr.starbuckslike.delivery.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Data
@NoArgsConstructor
@Builder
public class DeliveryDto {
    //DTD는 사용자의 요청이나 응답이 필요한

    private Long id;
    private String alias; // 배송지 별칭
    //private String name;
    private String address; //주소
    private int phone; // 휴대폰 번호
    private String userUid;
    private String memo; //배송지 메모

    //   private String basic_flag;


    @QueryProjection
    public DeliveryDto(Long id, String alias, String address, int phone, String userUid) {
        this.id = id;
        this.alias = alias;
        this.address = address;
        this.phone = phone;
        this.userUid = userUid;
        this.memo = memo;
    }
}

