package com.hummingbird.kr.starbuckslike.purchase.application;

import com.hummingbird.kr.starbuckslike.purchase.dto.out.PurchaseListResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface PurchaseService {

    List<PurchaseListResponseDto> findPurchaseByUuid(String memberUuid, Integer year);

    Slice<PurchaseListResponseDto> searchPurchaseByUuid(Pageable pageable, String memberUuid, Integer year);
}
