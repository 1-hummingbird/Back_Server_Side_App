package com.hummingbird.kr.starbuckslike.delivery.presentation;

import com.hummingbird.kr.starbuckslike.delivery.application.ShippingAddressService;
import com.hummingbird.kr.starbuckslike.delivery.dto.in.ShippingAddressRequestDTo;
import com.hummingbird.kr.starbuckslike.delivery.dto.in.ShippingAddressUpdateDto;
import com.hummingbird.kr.starbuckslike.delivery.vo.in.ShippingAddressRequestVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/shipping-address")
@RestController
public class ShippingAddressController {

    private final ShippingAddressService shippingAddressService;

    @PostMapping
    public void createShippingAddress(
            @RequestBody ShippingAddressRequestVo shippingAddressRequestVo
    ) {
        log.info("shippingAddressRequestVo : {}", shippingAddressRequestVo);
        shippingAddressService.addShippingAddress(
                ShippingAddressRequestDTo.from(shippingAddressRequestVo, "userUuid")
        );
    }
}
