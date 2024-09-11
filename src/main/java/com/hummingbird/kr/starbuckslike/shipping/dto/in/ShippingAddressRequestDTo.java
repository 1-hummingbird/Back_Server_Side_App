package com.hummingbird.kr.starbuckslike.shipping.dto.in;


import com.hummingbird.kr.starbuckslike.shipping.domain.ShippingAddress;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShippingAddressRequestDTo {

    private String userUuid;
    private String addressNickname;
    private String name;
    private String address;
    private String primaryPhone;
    private String secondaryPhone;
    private String memo;
    private Boolean defaultAddress;

    public ShippingAddressRequestDTo(String userUuid,
                                     String addressNickname,
                                     String name, String address,
                                     String primaryPhone,
                                     String secondaryPhone,
                                     String memo,
                                     Boolean defaultAddress
    ) {
        this.userUuid = userUuid;
        this.addressNickname = addressNickname;
        this.name = name;
        this.address = address;
        this.primaryPhone = primaryPhone;
        this.secondaryPhone = secondaryPhone;
        this.memo = memo;
        this.defaultAddress = defaultAddress;
    }

    public ShippingAddress toEnity(ShippingAddressRequestDTo shippingAddressRequestDTo){
        return ShippingAddress.builder()
                .addressNickname(shippingAddressRequestDTo.getAddressNickname())
                .name(shippingAddressRequestDTo.getName())
                .address(shippingAddressRequestDTo.getAddress())
                .primaryPhone(shippingAddressRequestDTo.getPrimaryPhone())
                .secondaryPhone(shippingAddressRequestDTo.getSecondaryPhone())
                .userUuid(shippingAddressRequestDTo.getUserUuid())
                .memo(shippingAddressRequestDTo.getMemo())
                .defaultAddress(shippingAddressRequestDTo.getDefaultAddress())
                .build();
    }
}
