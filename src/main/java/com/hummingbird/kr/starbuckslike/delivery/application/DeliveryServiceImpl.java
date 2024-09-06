package com.hummingbird.kr.starbuckslike.delivery.application;

import com.hummingbird.kr.starbuckslike.delivery.dto.DeliveryRequestDto;
import com.hummingbird.kr.starbuckslike.delivery.infrastructure.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService{

    private final DeliveryRepository deliveryRepository;

    //배송지 추가
    @Override
    public void createDelivery(DeliveryRequestDto deliveryRequestDto) {
        deliveryRepository.save(deliveryRequestDto.toEntity());
    }

    @Override
    public void updateDelivery(DeliveryRequestDto deliveryRequestDto) {
        deliveryRepository.save((deliveryRequestDto.toEntity()));

    }


}
