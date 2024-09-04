package com.hummingbird.kr.starbuckslike.delivery.application;

import com.hummingbird.kr.starbuckslike.delivery.dto.DeliveryRequestDto;

public interface DeliveryService {
    void createDelivery(DeliveryRequestDto deliveryRequestDto);

//    List<DeliveryResponseDto> getDeliveryListByUserUUid(String uuid);
}


//todo DeliveryRequestDto 만들기
//DeliveryRequestDto 요청