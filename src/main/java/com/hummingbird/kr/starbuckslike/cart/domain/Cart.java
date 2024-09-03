package com.hummingbird.kr.starbuckslike.cart.domain;



/**
 * 장바구니 엔티티
 * @author 허정현
 */

import com.hummingbird.kr.starbuckslike.common.entity.BaseEntity;
import com.hummingbird.kr.starbuckslike.product.domain.ProductOption;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "cart")
public class Cart extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id; // 장바구니 아이템 id

    @Column(name = "user_uuid" , nullable = false, length = 100)
    private String userUid; // 유저 uuid

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="option_id")
    private ProductOption productOption; // 상품 옵션

    @Column(name = "qty" , nullable = false)
    private Integer qty; // 수량

    @Column(name = "input_data" , nullable = true , length = 80)
    private String inputData; // 각인정보 같은 입력 데이터

}
