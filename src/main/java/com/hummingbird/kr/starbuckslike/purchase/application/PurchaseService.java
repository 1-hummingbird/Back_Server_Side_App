package com.hummingbird.kr.starbuckslike.purchase.application;

import com.hummingbird.kr.starbuckslike.purchase.dto.out.PurchaseListResponseDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface PurchaseService {

    List<PurchaseListResponseDto> findPurchaseByUuid(String memberUuid);
}
