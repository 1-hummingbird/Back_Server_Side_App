package com.hummingbird.kr.starbuckslike.shipping.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShippingAddressUpdateDto {

    private Long id;
    private String addressNickname;
    private String name;
    private String address;
    private String primaryPhone;
    private String secondaryPhone;
    private String memo;
    private Boolean defaultAddress;

    @Builder //생성자 생성..
    public ShippingAddressUpdateDto(
            Long id,
            String addressNickname,
            String name,
            String address,
            String primaryPhone,
            String secondaryPhone,
            String memo,
            Boolean defaultAddress
    ) {
        this.id = id;
        this.addressNickname = addressNickname;
        this.name = name;
        this.address = address;
        this.primaryPhone = primaryPhone;
        this.secondaryPhone = secondaryPhone;
        this.memo = memo;
        this.defaultAddress = defaultAddress;
    }
}
