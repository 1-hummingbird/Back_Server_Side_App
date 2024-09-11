package com.hummingbird.kr.starbuckslike.shipping.application;

import com.hummingbird.kr.starbuckslike.shipping.infrastructure.ShippingAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShippingAddressServiceImpl {
    private final ShippingAddressRepository shippingAddressRepository;
}
