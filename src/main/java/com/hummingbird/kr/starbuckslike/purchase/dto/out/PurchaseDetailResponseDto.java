package com.hummingbird.kr.starbuckslike.purchase.dto.out;

import com.hummingbird.kr.starbuckslike.purchase.vo.out.PurchaseDetailResponseVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PurchaseDetailResponseDto {

    private LocalDateTime purchaseDate; // 주문일
    // todo 주문 코드
    private Long totalPrice; // 총 금액
    private Long totalDiscount; // 총 할인 금액

    private String userName; // 주문자 이름
    private String address; // 주소
    private String primaryPhone; // 전화번호 1 (필수)
    private String secondaryPhone; // 전화번호 2
    private String memo; // 요청사항

    public PurchaseDetailResponseVo toVo(){
        return PurchaseDetailResponseVo.builder()
                .purchaseDate(purchaseDate)
                .totalPrice(totalPrice)
                .totalDiscount(totalDiscount)
                .userName(userName)
                .address(address)
                .primaryPhone(primaryPhone)
                .secondaryPhone(secondaryPhone)
                .memo(memo)
                .build();
    }

    @QueryProjection
    public PurchaseDetailResponseDto(LocalDateTime purchaseDate, Long totalPrice, Long totalDiscount, String userName,
                                     String address, String primaryPhone, String secondaryPhone, String memo) {
        this.purchaseDate = purchaseDate;
        this.totalPrice = totalPrice;
        this.totalDiscount = totalDiscount;
        this.userName = userName;
        this.address = address;
        this.primaryPhone = primaryPhone;
        this.secondaryPhone = secondaryPhone;
        this.memo = memo;
    }
}
