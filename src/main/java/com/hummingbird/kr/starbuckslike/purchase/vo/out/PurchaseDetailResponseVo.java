package com.hummingbird.kr.starbuckslike.purchase.vo.out;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PurchaseDetailResponseVo {
    private LocalDateTime purchaseDate; // 주문일
    private String purchaseCode; // 주문코드
    private Long totalPrice; // 총 금액
    private Long totalDiscount; // 총 할인 금액

    private String userName; // 주문자 이름
    private String address; // 주소
    private String primaryPhone; // 전화번호 1 (필수)
    private String secondaryPhone; // 전화번호 2
    private String memo; // 요청사항
}
