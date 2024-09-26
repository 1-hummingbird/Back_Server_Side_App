package com.hummingbird.kr.starbuckslike.purchase.infrastructure.search;

import com.hummingbird.kr.starbuckslike.purchase.dto.out.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.hummingbird.kr.starbuckslike.product.domain.QProductImage.productImage;
import static com.hummingbird.kr.starbuckslike.purchase.domain.QPurchase.purchase;
import static com.hummingbird.kr.starbuckslike.purchase.domain.QPurchaseProduct.purchaseProduct;
import static com.hummingbird.kr.starbuckslike.review.domain.QReview.review;

@Repository
@RequiredArgsConstructor
@Log4j2
public class PurchaseSearchImpl implements PurchaseSearch{
    private final JPAQueryFactory queryFactory;

    @Override
    public Slice<PurchaseListResponseDto> searchPurchaseByUuid(Pageable pageable, String memberUID, Integer year) {
        // 주문목록 Dto 를 상품목록 필드는 비워두고 조회
        List<PurchaseListResponseDto> purchaseList =
                queryFactory
                        .select(new QPurchaseListResponseDto(purchase.code, purchase.id,
                                                             purchase.createdAt, purchase.totalPrice)
                        )
                        .from(purchase)
                        .where(
                                purchase.memberUID.eq(memberUID)
                                .and(purchase.isDelete.isFalse()),
                                searchYearCondition(year)
                        )
                        .orderBy(purchase.createdAt.desc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize() + 1) // 다음 페이지가 있는지 확인하기 위해 1개 더 가져옴
                        .fetch();
        // 구매번호만 뽑음
        List<Long> purchaseIds = purchaseList
                .stream()
                .map(PurchaseListResponseDto::getPurchaseId)
                .toList();
        // 구매번호에 맞는 상품들을 가져옴
        List<PurchaseItemResponseDto> purchaseItems = getPurchaseItems(purchaseIds);
        // 구매번호 : 구매한상품들 그룹핑
        Map<Long, List<PurchaseItemResponseDto>>
                purchaseItemMap = purchaseItems
                .stream()
                .collect(Collectors.groupingBy(PurchaseItemResponseDto::getPurchaseId));
        // 주문 <-> 주문상품들 조립
        purchaseList.forEach(plist-> plist.setPurchaseItems(purchaseItemMap.get(plist.getPurchaseId())));



        boolean hasNext = purchaseList.size() > pageable.getPageSize();
        // Slice 크기에 맞게 자르기
        if (hasNext) {
            purchaseList.remove(purchaseList.size() - 1); // 페이징 사이즈 + 1 만큼 가져왔으므로 마지막 한 개 제거
        }
        return new SliceImpl<>(purchaseList, pageable, hasNext);

    }


    @Override
    public PurchaseDetailResponseDto findPurchaseDetailById(String purchaseCode) {
        return queryFactory
                .select( new QPurchaseDetailResponseDto(purchase.createdAt, purchase.code,
                                purchase.totalPrice, purchase.totalDiscount,
                                purchase.userName, purchase.address, purchase.primaryPhone,
                                new CaseBuilder()
                                    .when(purchase.secondaryPhone.isNull())
                                        .then("없음") // null 이면 "없음" 출력
                                        .otherwise(purchase.secondaryPhone),
                                purchase.memo)
                )
                .from(purchase)
                .where(
                        purchase.code.eq(purchaseCode)
                        .and(purchase.isDelete.eq(false))
                )
                .fetchOne();
    }

    @Override
    public Boolean exists(String purchaseCode) {
        Integer fetchOne = queryFactory
                .selectOne()
                .from(purchase)
                .where(purchase.code.eq(purchaseCode))
                .fetchFirst();
        return fetchOne != null;
    }

    // 구매번호에 해당하는 상품들을 조회
    public List<PurchaseItemResponseDto> getPurchaseItems(List<Long> purchaseIds){
        return  queryFactory
                    .select(new QPurchaseItemResponseDto(
                                purchaseProduct.purchase.id, purchaseProduct.optionId,
                                productImage.imageUrl, purchaseProduct.optionName,
                                purchaseProduct.price, purchaseProduct.qty ,
                                // 리뷰 작성 가능 여부 Boolean
                                Expressions.booleanTemplate(
                                    "case when {0} then true else false end", isReviewableCondition()
                                )
                            )
                    )
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

    /**
     * 주문목록의 모든 검색 조건
     */
    private BooleanExpression searchYearCondition(Integer year) {
        if (year == null) {
            // 현재 날짜와 6개월 전 날짜 계산
            LocalDate currentDate = LocalDate.now();
            LocalDate sixMonthsAgo = currentDate.minusMonths(6);
            // 디폴트일 경우 최근 6개월 동안 조회
            return isCurrentDateBetween(sixMonthsAgo, currentDate);
        }

        // 년도 조건
        return Expressions.numberTemplate(Integer.class,
                "YEAR({0})", purchase.createdAt).eq(year);
    }
    // 최근 6개월 조회
    private BooleanExpression isCurrentDateBetween(LocalDate from, LocalDate to) {
        return purchase.createdAt.between(
                Expressions.dateTemplate(LocalDateTime.class, "{0}", from.atStartOfDay()),
                Expressions.dateTemplate(LocalDateTime.class, "{0}", to.atTime(LocalTime.MAX)) // 날짜의 끝 시점
        );
    }
    // 리뷰 작성가능 조건
    private BooleanExpression isReviewableCondition() {
        LocalDate sixtyDaysAgo = LocalDate.now().minusDays(60);
        LocalDate currentDate = LocalDate.now();
        return purchaseProduct.createdAt.between(
                    Expressions.dateTemplate(LocalDateTime.class, "{0}", sixtyDaysAgo.atStartOfDay()), // 60일 전
                    Expressions.dateTemplate(LocalDateTime.class, "{0}", currentDate.atTime(LocalTime.MAX)) // 오늘
                )
                .and(purchaseProduct.isReviewed.isFalse()); // 이전에 리뷰 작성된 적 없어야 함
    }




}
