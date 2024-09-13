package com.hummingbird.kr.starbuckslike.shipping.application;

import com.hummingbird.kr.starbuckslike.shipping.dto.in.ShippingAddressDefaultChangeDto;
import com.hummingbird.kr.starbuckslike.shipping.dto.in.ShippingAddressRequestDto;
import com.hummingbird.kr.starbuckslike.shipping.dto.in.ShippingAddressUpdateDto;
import com.hummingbird.kr.starbuckslike.shipping.infrastructure.ShippingAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShippingAddressServiceImpl implements ShippingAddressService{
    private final ShippingAddressRepository shippingAddressRepository;

    @Override   //생성하는 부분 add address 트랜적션..
    public void addShippingAddress(ShippingAddressRequestDto shippingAddressRequestDTo) {
        shippingAddressRepository.save(shippingAddressRequestDTo.toEntity(shippingAddressRequestDTo));
    }

    @Override
    public void updateShippingAddress(ShippingAddressUpdateDto shippingAddressUpdateDto) {

    }

    @Override
    public void defaultShippingAddress(ShippingAddressDefaultChangeDto shippingAddressDefaultChangeDto) {

    }
}
