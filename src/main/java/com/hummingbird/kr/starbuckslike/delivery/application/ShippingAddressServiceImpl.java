package com.hummingbird.kr.starbuckslike.delivery.application;

import com.hummingbird.kr.starbuckslike.delivery.domain.ShippingAddress;
import com.hummingbird.kr.starbuckslike.delivery.dto.in.ShippingAddressDefaultChangeDto;
import com.hummingbird.kr.starbuckslike.delivery.dto.in.ShippingAddressRequestDTo;
import com.hummingbird.kr.starbuckslike.delivery.dto.in.ShippingAddressUpdateDto;
import com.hummingbird.kr.starbuckslike.delivery.dto.out.ShippingAddressResponseDto;
import com.hummingbird.kr.starbuckslike.delivery.infrastructure.ShippingAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ShippingAddressServiceImpl implements ShippingAddressService{

    private final ShippingAddressRepository shippingAddressRepository;

    @Override
    public void addShippingAddress(ShippingAddressRequestDTo shippingAddressRequestDTo) {
        shippingAddressRepository.save(shippingAddressRequestDTo.toEntity(shippingAddressRequestDTo));
    }

    @Override
    public void updateShippingAddress(ShippingAddressUpdateDto shippingAddressUpdateDto) {

    }

    @Override
    public void defaultShippingAddressChange(ShippingAddressDefaultChangeDto shippingAddressDefaultChangeDto) {

    }

    @Override
    public void deleteShippingAddress(Long shippingAddressId) {

    }

    @Override
    public List<ShippingAddressResponseDto> getShippingAddressListByUserUuid(String userUuid) {
        return List.of();
    }

    @Override
    public ShippingAddressResponseDto getShippingAddressById(Long shippingAddressId) {
        return null;
    }
}
