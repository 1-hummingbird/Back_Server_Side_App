package com.hummingbird.kr.starbuckslike.purchase.domain;

import com.hummingbird.kr.starbuckslike.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PurchaseProduct extends BaseEntity { // 주문 상품
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "purchase_id")
    private Purchase purchase; // 주문상품 <-> 주문 다대일

    @Column(name="qty" , nullable = false)
    private String qty; // 수량

    @Column( nullable = false)
    private Long price; // 구매 금액 (수량에 맞게)

    @Column(name="input_data" , nullable = false)
    private String inputData; // 상품 각인 데이터 같은 사용자 입력 내용

    // orderStatus
    @Column(name="is_shipped" , nullable = false)
	private Boolean isShipped; // 배송시작 여부
    @Column(name="is_delivered" , nullable = false)
    private Boolean isDelivered; // 배송완료 여부
    @Column(name="is_confirmed" , nullable = false)
    private Boolean isConfirmed; // 구매확정 여부

    // 택배사 구분번호
    // 운송장 번호


    @Column(name="productId", nullable = false)
    private Long productId; // 상품 id
    @Column(name="product_name", nullable = false, length = 100)
    private String productName; // 상품명

    @Column(name="option_Id", nullable = false)
    private Long optionId; // 옵션 id
    @Column(name="option_name", nullable = false, length = 100)
    private String optionName; // 옵션명






}
