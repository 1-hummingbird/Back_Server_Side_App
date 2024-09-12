package com.hummingbird.kr.starbuckslike.purchase.infrastructure.search;

import com.hummingbird.kr.starbuckslike.product.domain.QProductImage;
import com.hummingbird.kr.starbuckslike.product.dto.out.QProductListInfoResponseDto;
import com.hummingbird.kr.starbuckslike.purchase.domain.QPurchase;
import com.hummingbird.kr.starbuckslike.purchase.domain.QPurchaseProduct;
import com.hummingbird.kr.starbuckslike.purchase.dto.out.PurchaseDetailResponseDto;
import com.hummingbird.kr.starbuckslike.purchase.dto.out.PurchaseListResponseDto;
import com.hummingbird.kr.starbuckslike.purchase.dto.out.QPurchaseDetailResponseDto;
import com.hummingbird.kr.starbuckslike.purchase.dto.out.QPurchaseListResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.hummingbird.kr.starbuckslike.product.domain.QProductImage.productImage;
import static com.hummingbird.kr.starbuckslike.purchase.domain.QPurchase.purchase;
import static com.hummingbird.kr.starbuckslike.purchase.domain.QPurchaseProduct.purchaseProduct;

@Repository
@RequiredArgsConstructor
@Log4j2
public class PurchaseSearchImpl implements PurchaseSearch{
    private final JPAQueryFactory queryFactory;
    @Override
    public List<PurchaseListResponseDto> findPurchaseByUuid(String memberUuid) {
        List<PurchaseListResponseDto> purchaseData =
                queryFactory
                        .select(new QPurchaseListResponseDto(purchase.id, purchase.createdAt, purchase.totalPrice))
                        .from(purchase)
                        .where(
                                purchase.userUuid.eq(memberUuid)
                                // todo 날짜 범위 조건 추가
                                // todo 상품명으로 검색 조건 추가
                        )
                        .orderBy(purchase.createdAt.desc())
                        .fetch();
        // 구매번호만 뽑음
        List<Long>purchaseIds = purchaseData
                                    .stream()
                                    .map(PurchaseListResponseDto::getPurchaseId)
                                    .toList();
        // 구매번호에 맞는 상품들을 가져옴
        List<PurchaseDetailResponseDto> purchaseItems = getPurchaseItems(purchaseIds);
        // 구매번호 : 구매한상품들 그룹핑
        Map<Long, List<PurchaseDetailResponseDto>>
                       purchaseItemMap = purchaseItems
                                            .stream()
                                            .collect(Collectors.groupingBy(PurchaseDetailResponseDto::getPurchaseId));
        // dto
        purchaseData.forEach(plist-> plist.setPurchaseItems(purchaseItemMap.get(plist.getPurchaseId())));
        return purchaseData;
    }

    // 구매번호에 해당하는 상품들을 조회
    public List<PurchaseDetailResponseDto> getPurchaseItems(List<Long> purchaseIds){
        return  queryFactory
                    .select(new QPurchaseDetailResponseDto(purchaseProduct.purchase.id, purchaseProduct.optionId,
                            productImage.imageUrl, purchaseProduct.optionName, purchaseProduct.price, purchaseProduct.qty
                            ))
                    .from(purchaseProduct)
                    .leftJoin(productImage)
                    .on(
                            purchaseProduct.productId.eq(productImage.product.id)
                            .and(productImage.seq.eq(0)) // 메인 이미지만
                    )
                    .where(purchaseProduct.purchase.id.in(purchaseIds))
                    .orderBy(purchaseProduct.createdAt.desc())
                    .fetch();
    }


}
