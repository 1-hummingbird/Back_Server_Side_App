package com.hummingbird.kr.starbuckslike.shipping.vo.in;

import com.hummingbird.kr.starbuckslike.shipping.dto.in.ShippingAddressAddRequestDTO;

import lombok.Getter;

@Getter
public class ShippingAddressAddRequestVO {

    private String addressNickname;
    private String name;
    private String address;
    private String phone;
    private String memo;

    public ShippingAddressAddRequestDTO toDTO(String memberUID) {
        return ShippingAddressAddRequestDTO.builder()
                .addressNickname(this.addressNickname)
                .name(this.name)
                .address(this.address)
                .phone(this.phone)
                .memo(this.memo)
                .memberUID(memberUID)
                .build();
    }

}
