package com.hummingbird.kr.starbuckslike.delivery.presentation;

import com.hummingbird.kr.starbuckslike.common.entity.CommonResponseEntity;
import com.hummingbird.kr.starbuckslike.delivery.application.DeliveryService;
import com.hummingbird.kr.starbuckslike.delivery.dto.DeliveryRequestDto;
import com.hummingbird.kr.starbuckslike.delivery.infrastructure.DeliveryRepository;
import com.hummingbird.kr.starbuckslike.delivery.vo.DeliveryRequestVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j  //log보려고..작성함
@RequestMapping("/api/v1/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping
    public CommonResponseEntity<Void> CreateDelivery(
            @RequestBody DeliveryRequestVo deliveryRequestVo) {
        DeliveryRequestDto deliveryRequestDto = DeliveryRequestDto.builder()
                .id(deliveryRequestVo.getId())
    }






}
