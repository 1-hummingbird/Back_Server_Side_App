package com.hummingbird.kr.starbuckslike.purchase.vo.in;

import lombok.Getter;
import java.util.List;

@Getter
public class AddPurchaseRequestVo {
    private Long totalPrice; // 총 구매금액
    private Long totalDiscount; // 총 할인금액

    private String address; // 주소
    private String primaryPhone;
    private String secondaryPhone;
    private String userName;
    private String memo; // 요청사항
    private List<AddPurchaseItemRequestVo> purchaseProducts; // 주문 상품옵션
}
