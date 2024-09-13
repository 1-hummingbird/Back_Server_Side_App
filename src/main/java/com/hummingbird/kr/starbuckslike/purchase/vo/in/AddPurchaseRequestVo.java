package com.hummingbird.kr.starbuckslike.purchase.vo.in;

import jakarta.persistence.Column;
import lombok.Getter;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AddPurchaseRequestVo {
    private Long totalPrice; // 총 구매금액
    private String address; // 주소
    private String primaryPhone;
    private String secondaryPhone;
    private String userName;
    private String userUuid;
    private String memo; // 요청사항
    private List<AddPurchaseItemRequestVo> addPurchaseItemRequestVos; // 주문 상품옵션
}
