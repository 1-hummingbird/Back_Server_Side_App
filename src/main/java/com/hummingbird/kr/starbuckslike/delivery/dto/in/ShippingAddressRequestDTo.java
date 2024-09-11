package com.hummingbird.kr.starbuckslike.delivery.dto.in;

import com.hummingbird.kr.starbuckslike.delivery.domain.ShippingAddress;
import com.hummingbird.kr.starbuckslike.delivery.vo.in.ShippingAddressRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ShippingAddressRequestDTo {

    private String userUuid;
    private String addressNickname;
    private String name;
    private String address;
    private String primaryPhone;
    private String secondaryPhone;
    private String memo;
    private Boolean defaultAddress;

    @Builder
    public ShippingAddressRequestDTo(
            String userUuid,
            String addressNickname,
            String name,
            String address,
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

    public ShippingAddress toEntity(ShippingAddressRequestDTo shippingAddressRequestDTo){
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

    public static ShippingAddressRequestDTo from(ShippingAddressRequestVo shippingAddressRequestVo, String userUuid) {
        return ShippingAddressRequestDTo.builder()
                .userUuid(userUuid)
                .addressNickname(shippingAddressRequestVo.getAddressNickname())
                .name(shippingAddressRequestVo.getName())
                .address(shippingAddressRequestVo.getAddress())
                .primaryPhone(shippingAddressRequestVo.getPrimaryPhone())
                .secondaryPhone(shippingAddressRequestVo.getSecondaryPhone())
                .memo(shippingAddressRequestVo.getMemo())
                .defaultAddress(shippingAddressRequestVo.getDefaultAddress())
                .build();
    }
}
