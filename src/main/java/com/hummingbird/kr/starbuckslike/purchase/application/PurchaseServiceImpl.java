package com.hummingbird.kr.starbuckslike.purchase.application;

import com.hummingbird.kr.starbuckslike.common.utils.CategoryCodeGenerator;
import com.hummingbird.kr.starbuckslike.common.utils.PurchaseCodeGenerator;
import com.hummingbird.kr.starbuckslike.purchase.domain.Purchase;
import com.hummingbird.kr.starbuckslike.purchase.domain.PurchaseProduct;
import com.hummingbird.kr.starbuckslike.purchase.dto.in.AddPurchaseRequestDto;
import com.hummingbird.kr.starbuckslike.purchase.dto.out.PurchaseDetailResponseDto;
import com.hummingbird.kr.starbuckslike.purchase.dto.out.PurchaseListResponseDto;
import com.hummingbird.kr.starbuckslike.purchase.infrastructure.PurchaseProductRepository;
import com.hummingbird.kr.starbuckslike.purchase.infrastructure.PurchaseRepository;
import com.hummingbird.kr.starbuckslike.purchase.infrastructure.search.PurchaseSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class PurchaseServiceImpl implements PurchaseService{
    private final PurchaseSearch purchaseSearch;
    private final PurchaseRepository purchaseRepository;
    private final PurchaseProductRepository purchaseProductRepository;

    private static final int MAX_CODE_TRIES = 10;  // 최대 재시도 횟수

    @Override
    public List<PurchaseListResponseDto> findPurchaseByUuid(String memberUuid, Integer year) {
        return purchaseSearch.findPurchaseByUuid(memberUuid, year);
    }

    @Override
    public Slice<PurchaseListResponseDto> searchPurchaseByUuid(Pageable pageable, String memberUuid, Integer year) {
        return purchaseSearch.searchPurchaseByUuid(pageable, memberUuid, year);
    }

    @Override
    public PurchaseDetailResponseDto findPurchaseDetailById(String purchaseCode) {
        return purchaseSearch.findPurchaseDetailById(purchaseCode);
    }

    @Override
    public void addPurchase(AddPurchaseRequestDto addPurchaseRequestDto) {
        if(addPurchaseRequestDto.getPurchaseProducts().isEmpty()){
            throw new IllegalArgumentException("주문 항목이 비어 있습니다. 최소한 하나 이상의 상품을 포함해야 합니다.");
        }
        // 주문 생성
        Purchase purchase = addPurchaseRequestDto.toPurchase(generateUniquePurchaseCode());
        purchaseRepository.save(purchase);
        // 주문 상품 생성
        List<PurchaseProduct> purchaseProducts = addPurchaseRequestDto.toPurchaseProduct(purchase);
        purchaseProductRepository.saveAll(purchaseProducts); // 주문 상품들을 저장
    }

    @Override
    public void deletePurchase(Long purchaseId) {
        // 기존의 purchase 엔티티를 조회
        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문이 존재하지 않습니다."));

        purchaseRepository.softDeletePurchase(purchaseId);


    }

    private String generateUniquePurchaseCode() {
        for (int i = 0; i < MAX_CODE_TRIES; i++) {
            String code = PurchaseCodeGenerator.generateOrderCode();
            if(!purchaseRepository.existsByCode(code)){
                return code;
            }
        }
        throw new IllegalStateException("고유한 주문코드를 생성하는 데 실패했습니다.");
    }
}
