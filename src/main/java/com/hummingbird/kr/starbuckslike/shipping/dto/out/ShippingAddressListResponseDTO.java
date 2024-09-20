package com.hummingbird.kr.starbuckslike.shipping.dto.out;

import com.hummingbird.kr.starbuckslike.shipping.domain.ShippingAddress;
import java.util.List;

public class ShippingAddressListResponseDTO {

    private List<ShippingAddress> shippingAddressList;

    public ShippingAddressListResponseDTO(List<ShippingAddress> shippingAddressList) {
        this.shippingAddressList = shippingAddressList;
    }

    public List<ShippingAddress> getShippingAddressList() {
        return shippingAddressList;
    }
}
