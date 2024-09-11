package com.hummingbird.kr.starbuckslike.shipping.application;

import com.hummingbird.kr.starbuckslike.shipping.dto.in.ShippingAddressRequestDTo;
import com.hummingbird.kr.starbuckslike.shipping.dto.in.ShippingAddressUpdateDto;

import java.util.List;

public interface ShippingAddressService {

    void addShippingAddress(ShippingAddressRequestDTo shippingAddressRequestDTo);
    void updateShippingAddress(ShippingAddressRequestDTo shippingAddressRequestDTo);
    void defaultShippingAddress(ShippingAddressRequestDTo shippingAddressRequestDTo);


    List<ShippingAddressRequestDTo> getShippingAddressListByUserUuid(String userUuid);
    ShippingAddressUpdateDto getShippingAddressById(Long ShippingAddressId);
}
