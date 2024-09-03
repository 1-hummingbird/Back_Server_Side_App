package com.hummingbird.kr.starbuckslike.delivery.infrastructure.search;

import com.hummingbird.kr.starbuckslike.delivery.dto.DeliveryDto;

import java.util.List;

public interface DeliverySearch {

    // 유저 UUID로 유저 배송지 리스트 조회
    List<DeliveryDto> getDeliveryDtoListById(String userUid);
}
