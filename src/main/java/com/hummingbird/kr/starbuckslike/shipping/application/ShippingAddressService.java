package com.hummingbird.kr.starbuckslike.shipping.application;

import com.hummingbird.kr.starbuckslike.shipping.dto.in.ShippingAddressDefaultChangeDto;
import com.hummingbird.kr.starbuckslike.shipping.dto.in.ShippingAddressRequestDto;
import com.hummingbird.kr.starbuckslike.shipping.dto.in.ShippingAddressUpdateDto;


public interface ShippingAddressService {

    void addShippingAddress(ShippingAddressRequestDto shippingAddressRequestDTo);
    void updateShippingAddress(ShippingAddressUpdateDto shippingAddressUpdateDto);
    void defaultShippingAddress(ShippingAddressDefaultChangeDto shippingAddressDefaultChangeDto);

//
//    List<ShippingAddressRequestDTo> getShippingAddressListByUserUuid(String userUuid);
//    ShippingAddressUpdateDto getShippingAddressById(Long ShippingAddressId);
}
