package com.hummingbird.kr.starbuckslike.purchase.infrastructure.search;

import com.hummingbird.kr.starbuckslike.purchase.dto.out.PurchaseDetailResponseDto;
import com.hummingbird.kr.starbuckslike.purchase.dto.out.PurchaseListResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface PurchaseSearch {

    // 주문목록 조회 테스트
    List<PurchaseListResponseDto> findPurchaseByUuid(String memberUuid, Integer year);
    // 주문목록 조회 [페이징 및 검색조건 적용]
    Slice<PurchaseListResponseDto> searchPurchaseByUuid(Pageable pageable, String memberUuid, Integer year);

    // 주문 디테일 조회 (총가격 , 총 할인금액, 배송지)
    PurchaseDetailResponseDto findPurchaseDetailById(Long purchaseId);

}
