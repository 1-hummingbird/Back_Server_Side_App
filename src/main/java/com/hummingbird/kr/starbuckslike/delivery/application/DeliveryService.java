package com.hummingbird.kr.starbuckslike.delivery.application;

import com.hummingbird.kr.starbuckslike.delivery.dto.DeliveryRequestDto;

import java.util.List;

public interface DeliveryService {
    void creatDelivery(DeliveryRequestDto deliveryRequestDto);

//    List<DeliveryResponseDto> getDeliveryListByUserUUid(String uuid);
}


//todo DeliveryRequestDto 만들기
//DeliveryRequestDto 요청