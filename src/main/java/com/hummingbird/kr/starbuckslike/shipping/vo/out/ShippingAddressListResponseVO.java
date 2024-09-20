package com.hummingbird.kr.starbuckslike.shipping.vo.out;

import com.hummingbird.kr.starbuckslike.shipping.domain.ShippingAddress;
import com.hummingbird.kr.starbuckslike.shipping.dto.out.ShippingAddressListResponseDTO;
import java.util.List;

public class ShippingAddressListResponseVO {
    private List<ShippingAddress> shippingAddressList;

    public ShippingAddressListResponseVO(ShippingAddressListResponseDTO shippingAddressResponseDTO) {
        this.shippingAddressList = shippingAddressResponseDTO.getShippingAddressList();
    }

    public List<ShippingAddress> getShippingAddressList() {
        return shippingAddressList;
    }
}
