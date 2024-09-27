package com.hummingbird.kr.starbuckslike.shipping.dto.in;

import lombok.Getter;
import com.hummingbird.kr.starbuckslike.shipping.vo.in.ShippingAddressGetDetailRequestVO;

@Getter
public class ShippingAddressGetDetailRequestDTO {
    private Long id;
    private String memberUID;

    public ShippingAddressGetDetailRequestDTO(Long id, String memberUID) {
        this.id = id;
        this.memberUID = memberUID;
    }

    public static ShippingAddressGetDetailRequestDTO from(ShippingAddressGetDetailRequestVO requestVO, String memberUID) {
        return new ShippingAddressGetDetailRequestDTO(requestVO.getId(), memberUID);
    }
}
