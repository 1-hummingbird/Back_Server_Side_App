package com.hummingbird.kr.starbuckslike.purchase.application;

import com.hummingbird.kr.starbuckslike.common.utils.CategoryCodeGenerator;
import com.hummingbird.kr.starbuckslike.common.utils.PurchaseCodeGenerator;
import com.hummingbird.kr.starbuckslike.purchase.domain.Purchase;
import com.hummingbird.kr.starbuckslike.purchase.domain.PurchaseProduct;
import com.hummingbird.kr.starbuckslike.purchase.dto.in.*;
import com.hummingbird.kr.starbuckslike.purchase.dto.out.*;
import com.hummingbird.kr.starbuckslike.purchase.infrastructure.PurchaseProductRepository;
import com.hummingbird.kr.starbuckslike.purchase.infrastructure.PurchaseRepository;
import com.hummingbird.kr.starbuckslike.purchase.infrastructure.search.PurchaseSearch;
import com.hummingbird.kr.starbuckslike.common.Exception.BaseException;
import com.hummingbird.kr.starbuckslike.common.entity.BaseResponseStatus;

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
    public List<PurchaseListResponseDto> findPurchaseByUuid(String memberUID, Integer year) {
        return purchaseSearch.findPurchaseByUuid(memberUID, year);
    }

    @Override
    public Slice<PurchaseListResponseDto> searchPurchaseByUuid(Pageable pageable, String memberUID, Integer year) {
        return purchaseSearch.searchPurchaseByUuid(pageable, memberUID, year);
    }

    @Override
    public PurchaseDetailResponseDto findPurchaseDetailById(String purchaseCode) {
        return purchaseSearch.findPurchaseDetailById(purchaseCode);
    }

    @Override
    public void addPurchase(AddPurchaseRequestDto addPurchaseRequestDto) {
        if(addPurchaseRequestDto.getPurchaseProducts().isEmpty()){
            throw new BaseException(BaseResponseStatus.DISALLOWED_ACTION);
        }
        // 주문 생성
        Purchase purchase = addPurchaseRequestDto.toPurchase(generateUniquePurchaseCode());
        purchaseRepository.save(purchase);
        // 주문 상품 생성
        List<PurchaseProduct> purchaseProducts = addPurchaseRequestDto.toPurchaseProduct(purchase);
        purchaseProductRepository.saveAll(purchaseProducts); // 주문 상품들을 저장
    }

    @Override
    public void deletePurchase(String purchaseCode) {
        // 기존의 purchase 엔티티를 조회
        Purchase purchase = purchaseRepository.findByCode(purchaseCode)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_INTEREST));
        purchaseRepository.softDeletePurchase(purchaseCode);
    }

    private String generateUniquePurchaseCode() {
        for (int i = 0; i < MAX_CODE_TRIES; i++) {
            String code = PurchaseCodeGenerator.generateOrderCode();
            if(!purchaseRepository.existsByCode(code)){
                return code;
            }
        }
        throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
    }
}
