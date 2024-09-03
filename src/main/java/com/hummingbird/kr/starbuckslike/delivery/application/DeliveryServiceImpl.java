package com.hummingbird.kr.starbuckslike.delivery.application;

import com.hummingbird.kr.starbuckslike.delivery.dto.DeliveryRequestDto;
import com.hummingbird.kr.starbuckslike.delivery.infrastructure.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeliveryServiceImpl implements DeliveryService{
    private final DeliveryRepository deliveryRepository;
    @Override
    public void creatDelivery(DeliveryRequestDto deliveryRequestDto) {

        deliveryRepository.save(deliveryRequestDto.toEntity());

    }
}
