package com.hummingbird.kr.starbuckslike.shipping.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import com.hummingbird.kr.starbuckslike.shipping.vo.out.ShippingAddressGetDetailResponseVO;
import com.hummingbird.kr.starbuckslike.shipping.domain.ShippingAddress;

@Builder
@AllArgsConstructor
public class ShippingAddressGetDetailResponseDTO {

    private String addressNickname;
    private String name;
    private String address;
    private String Phone;
    private String memo;

    public static ShippingAddressGetDetailResponseDTO fromEntity(ShippingAddress shippingAddress) {
        return ShippingAddressGetDetailResponseDTO.builder()
                .addressNickname(shippingAddress.getAddressNickname())
                .name(shippingAddress.getName())
                .address(shippingAddress.getAddress())
                .Phone(shippingAddress.getPhone())
                .memo(shippingAddress.getMemo())
                .build();
    }

    public ShippingAddressGetDetailResponseVO toVO() {
        return ShippingAddressGetDetailResponseVO.builder()
                .addressNickname(addressNickname)
                .name(name)
                .address(address)
                .Phone(Phone)
                .memo(memo)
                .build();
    }

}
