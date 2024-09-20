package com.hummingbird.kr.starbuckslike.shipping.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import com.hummingbird.kr.starbuckslike.shipping.vo.out.ShippingAddressGetDefaultResponseVO;
import com.hummingbird.kr.starbuckslike.shipping.domain.ShippingAddress;

@Builder
@AllArgsConstructor
public class ShippingAddressGetDefaultResponseDTO {

    private String addressNickname;
    private String name;
    private String address;
    private String Phone;
    private String memo;

    public static ShippingAddressGetDefaultResponseDTO fromEntity(ShippingAddress shippingAddress) {
        return ShippingAddressGetDefaultResponseDTO.builder()
                .addressNickname(shippingAddress.getAddressNickname())
                .name(shippingAddress.getName())
                .address(shippingAddress.getAddress())
                .Phone(shippingAddress.getPhone())
                .memo(shippingAddress.getMemo())
                .build();
    }

    public ShippingAddressGetDefaultResponseVO toVO() {
        return ShippingAddressGetDefaultResponseVO.builder()
                .addressNickname(addressNickname)
                .name(name)
                .address(address)
                .Phone(Phone)
                .memo(memo)
                .build();
    }

}
