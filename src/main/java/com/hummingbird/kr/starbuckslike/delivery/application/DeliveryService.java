package com.hummingbird.kr.starbuckslike.delivery.application;

import com.hummingbird.kr.starbuckslike.delivery.dto.DeliveryRequestDto;

public interface DeliveryService {

    //배송지등록
    void createDelivery(DeliveryRequestDto deliveryRequestDto);


    //배송지수정
    void updateDelivery(DeliveryRequestDto deliveryRequestDto);
//    List<DeliveryResponseDto> getDeliveryListByUserUUid(String uuid);
}


//todo DeliveryRequestDto 만들기
//DeliveryRequestDto 요청