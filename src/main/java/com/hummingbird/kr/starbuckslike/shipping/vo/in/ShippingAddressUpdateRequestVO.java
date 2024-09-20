package com.hummingbird.kr.starbuckslike.shipping.vo.in;

import lombok.Getter;
import org.springframework.lang.Nullable;

import com.hummingbird.kr.starbuckslike.shipping.dto.in.ShippingAddressUpdateRequestDTO;

@Getter
public class ShippingAddressUpdateRequestVO {

    private Long shippingAddressID;
    private @Nullable String addressNickname;
    private @Nullable String name;
    private @Nullable String address;
    private @Nullable String phone;
    private @Nullable String memo;

    public ShippingAddressUpdateRequestDTO toDTO(String memberUID) {
        return ShippingAddressUpdateRequestDTO.builder()
                .shippingAddressID(this.shippingAddressID)
                .addressNickname(this.addressNickname)
                .name(this.name)
                .address(this.address)
                .phone(this.phone)
                .memo(this.memo)
                .memberUID(memberUID)
                .build();
    }
}
