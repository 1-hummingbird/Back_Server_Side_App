package com.hummingbird.kr.starbuckslike.delivery.dto;

import com.hummingbird.kr.starbuckslike.delivery.domain.Delivery;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryRequestDto {
    //DTo는 사용자의 요청이나 응답이 필요한

    private Long id;
    private String addressnickname; // 배송지 별칭
    private String name;
    private String address; //주소
    private String mainphone; // 휴대폰 번호 int로 하면 안된.
    private String secphone;
    private String userUuid;
    private String memo; //배송지 메모
    private Boolean isBasic;

    //   private String basic_flag;

    public Delivery toEntity(){
        return Delivery.builder()
                .userUuid(userUuid)
                .address(address)
                .isBasic(false) //엔티티생성할때 무조건 폴스..0이 들어간다.
                .mainphone(mainphone)
                .secphone(secphone)
                .memo(memo)
                .name(name)
                .addressnickname(addressnickname)
                .build();

    }



}

