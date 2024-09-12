package com.hummingbird.kr.starbuckslike.purchase.domain;

import com.hummingbird.kr.starbuckslike.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

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

    @Comment("수량")
    @Column(name="qty" , nullable = false)
    private Integer qty;

    @Comment("구매 금액 (수량에 맞게)")
    @Column( nullable = false )
    private Long price; //

    @Comment("할인 금액 (할인상품일경우, 수량에 맞게)")
    @Column
    private Long discountPrice;

    @Comment("상품 각인 데이터 같은 사용자 입력 내용")
    @Column(name="input_data")
    private String inputData;

    @Comment("상품 Id")
    @Column(name="productId", nullable = false)
    private Long productId;
    @Comment("상품명")
    @Column(name="product_name", nullable = false, length = 100)
    private String productName;

    @Comment("옵션 id")
    @Column(name="option_Id", nullable = false)
    private Long optionId;
    @Comment("옵션명")
    @Column(name="option_name", nullable = false, length = 100)
    private String optionName;

    // orderStatus
    @Comment("배송시작 여부")
    @Column(name="is_shipped" , nullable = false)
	private Boolean isShipped;
    @Comment("배송완료 여부")
    @Column(name="is_delivered" , nullable = false)
    private Boolean isDelivered;
    @Comment("구매확정 여부")
    @Column(name="is_confirmed" , nullable = false)
    private Boolean isConfirmed;

    // 택배사 구분번호
    // 운송장 번호


    @Builder

    public PurchaseProduct(Purchase purchase, Integer qty, Long price, Long discountPrice, String inputData,
                           Long productId, String productName, Long optionId, String optionName,
                           Boolean isShipped, Boolean isDelivered, Boolean isConfirmed) {
        this.purchase = purchase;
        this.qty = qty;
        this.price = price;
        this.discountPrice = discountPrice;
        this.inputData = inputData;
        this.productId = productId;
        this.productName = productName;
        this.optionId = optionId;
        this.optionName = optionName;
        this.isShipped = isShipped;
        this.isDelivered = isDelivered;
        this.isConfirmed = isConfirmed;
    }
}
