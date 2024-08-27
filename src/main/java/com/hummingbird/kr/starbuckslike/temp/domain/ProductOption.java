package com.hummingbird.kr.starbuckslike.temp.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import static jakarta.persistence.FetchType.LAZY;
/**
 * 상품 옵션 엔티티
 * @author 허정현
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product_option")
public class ProductOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private Long id;

    @ManyToOne(fetch = LAZY) // 상품옵션 <-> 상품  다대일 관계
    @JoinColumn(name="product_id") //
    private Product product;

    @Column(name = "option_name" , length = 200, nullable = false)
    private String name;

    @Column(name = "price" , nullable = false)
    private Integer price = 0;

    @Column(name = "qty" , nullable = false)
    private Integer quantity; // 수량

    @Column(name = "is_input_option" , nullable = false)
    @ColumnDefault("false")
    private Boolean isInputOption = false; // 사용자 입력 옵션 (각인 등) 여부

    @Column(name = "discount_rate")
    private Double discountRate= 0.00; // 할인율

    @Column(name = "status", nullable = false, length = 20)
    private SalesStatus status; // 판매 상태 : AVAILABLE, DISCONTINUED , HIDDEN
}
