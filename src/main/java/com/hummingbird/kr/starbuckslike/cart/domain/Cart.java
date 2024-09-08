package com.hummingbird.kr.starbuckslike.cart.domain;
/**
 * 장바구니 엔티티
 * @author 허정현
 */

import com.hummingbird.kr.starbuckslike.common.entity.BaseEntity;
import com.hummingbird.kr.starbuckslike.product.domain.ProductOption;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "cart")
public class Cart extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 장바구니 아이템 id

    @Column(name = "user_uuid" , nullable = false, length = 100)
    private String userUid; // 유저 uuid

    @Column(name="product_id")
    private Long productId; // 상품

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="option_id")
    private ProductOption productOption; // 상품 옵션

    @Column(name = "qty" , nullable = false)
    private Integer qty; // 수량

    @Column(name = "input_data" , nullable = true , length = 80)
    private String inputData; // 각인정보 같은 입력 데이터

    @Column(name = "is_checked", nullable = false)
    private Boolean isChecked;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    // 장바구니 아이템 수량 변경 (단순 상태 변경 메서드)
    public void changeQty(int qty) {
        this.qty = qty;
    }

    // 장바구니 아이템 선택/해제 (단순 상태 변경 메서드)
    public void toggleSelect() {
        this.isChecked = !this.isChecked;
    }

    @Builder
    public Cart(String userUid, Long productId, ProductOption productOption, Integer qty, String inputData,
                Boolean isChecked, Boolean isDeleted) {
        this.userUid = userUid;
        this.productId = productId;
        this.productOption = productOption;
        this.qty = qty;
        this.inputData = inputData;
        this.isChecked = isChecked;
        this.isDeleted = isDeleted;
    }
}
