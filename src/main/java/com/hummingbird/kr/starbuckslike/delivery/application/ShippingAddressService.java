package com.hummingbird.kr.starbuckslike.delivery.application;

import com.hummingbird.kr.starbuckslike.delivery.dto.in.ShippingAddressDefaultChangeDto;
import com.hummingbird.kr.starbuckslike.delivery.dto.in.ShippingAddressRequestDTo;
import com.hummingbird.kr.starbuckslike.delivery.dto.in.ShippingAddressUpdateDto;
import com.hummingbird.kr.starbuckslike.delivery.dto.out.ShippingAddressResponseDto;

import java.util.List;

public interface ShippingAddressService {
    void addShippingAddress(ShippingAddressRequestDTo shippingAddressRequestDTo);
    void updateShippingAddress(ShippingAddressUpdateDto shippingAddressUpdateDto);
    void defaultShippingAddressChange(ShippingAddressDefaultChangeDto shippingAddressDefaultChangeDto);
    void deleteShippingAddress(Long shippingAddressId);
    List<ShippingAddressResponseDto> getShippingAddressListByUserUuid(String userUuid);
    ShippingAddressResponseDto getShippingAddressById(Long shippingAddressId);
}
