package com.hummingbird.kr.starbuckslike.shipping.application;

import com.hummingbird.kr.starbuckslike.shipping.dto.in.ShippingAddressDefaultChangeDto;
import com.hummingbird.kr.starbuckslike.shipping.dto.in.ShippingAddressRequestDTo;
import com.hummingbird.kr.starbuckslike.shipping.dto.in.ShippingAddressUpdateDto;

import java.util.List;


public interface ShippingAddressService {

    void addShippingAddress(ShippingAddressRequestDTo shippingAddressRequestDTo);
    void updateShippingAddress(ShippingAddressUpdateDto shippingAddressUpdateDto);
    void defaultShippingAddress(ShippingAddressDefaultChangeDto shippingAddressDefaultChangeDto);

//
//    List<ShippingAddressRequestDTo> getShippingAddressListByUserUuid(String userUuid);
//    ShippingAddressUpdateDto getShippingAddressById(Long ShippingAddressId);
}
