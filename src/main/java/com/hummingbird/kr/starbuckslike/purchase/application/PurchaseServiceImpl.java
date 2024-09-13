package com.hummingbird.kr.starbuckslike.purchase.application;

import com.hummingbird.kr.starbuckslike.purchase.dto.out.PurchaseListResponseDto;
import com.hummingbird.kr.starbuckslike.purchase.infrastructure.search.PurchaseSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService{
    private final PurchaseSearch purchaseSearch;
    @Override
    public List<PurchaseListResponseDto> findPurchaseByUuid(String memberUuid, Integer year) {
        return purchaseSearch.findPurchaseByUuid(memberUuid, year);
    }

    @Override
    public Slice<PurchaseListResponseDto> searchPurchaseByUuid(Pageable pageable, String memberUuid, Integer year) {
        return purchaseSearch.searchPurchaseByUuid(pageable, memberUuid, year);
    }
}
