package com.hummingbird.kr.starbuckslike.purchase.infrastructure.search;

import com.hummingbird.kr.starbuckslike.purchase.dto.out.PurchaseListResponseDto;

import java.util.List;

public interface PurchaseSearch {

    List<PurchaseListResponseDto> findPurchaseByUuid(String memberUuid);

}
