package com.hummingbird.kr.starbuckslike.purchase.application;

import com.hummingbird.kr.starbuckslike.purchase.dto.in.AddPurchaseRequestDto;
import com.hummingbird.kr.starbuckslike.purchase.dto.out.PurchaseDetailResponseDto;
import com.hummingbird.kr.starbuckslike.purchase.dto.out.PurchaseListResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface PurchaseService {

    /**
     * Select
     */
    List<PurchaseListResponseDto> findPurchaseByUuid(String memberUID, Integer year);
    // 주문 목록 조회
    Slice<PurchaseListResponseDto> searchPurchaseByUuid(Pageable pageable, String memberUID, Integer year);

    // 주문 디테일 조회
    PurchaseDetailResponseDto findPurchaseDetailById(String purchaseCode);

    /**
     * Create , Update, Delete
     */
    // 주문
    @Transactional
    void addPurchase(AddPurchaseRequestDto addPurchaseRequestDto);

    @Transactional
    void deletePurchase(String purchaseCode);
}
