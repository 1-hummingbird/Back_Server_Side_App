package com.hummingbird.kr.starbuckslike.product.domain;

import com.hummingbird.kr.starbuckslike.common.entity.BaseEntity;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product_option")
public class ProductOption extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = LAZY) // 상품옵션 <-> 상품  다대일 관계
    @JoinColumn(name="product_id") //
    private Product product;

    @Column(name = "option_name" , length = 200, nullable = false)
    private String name;

    @Column(name = "price" , nullable = false)
    private Integer price;

    @Column(name = "quantity" , nullable = false)
    private Long quantity; // 수량

    @Column(name = "is_input_option" , nullable = false)
    @ColumnDefault("false")
    private Boolean isInputOption; // 사용자 입력 옵션 (각인 등) 여부

    @Column(name = "discount_rate")
    private Float discountRate; // 할인율

    @Column(nullable = false , name = "is_available")
    private Boolean isAvailable; // 판매 여부

    @Column(nullable = false , name = "is_hidden")
    private Boolean isHidden; // 숨김 여부

    @Column(name = "is_deleted", nullable = false)
    @ColumnDefault("false")
    private Boolean isDeleted; // 삭제 여부
    @Builder
    public ProductOption(Product product, String name, Integer price, Long quantity, Boolean isInputOption,
                         Float discountRate, Boolean isAvailable, Boolean isHidden, Boolean isDeleted) {
        this.product = product;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.isInputOption = isInputOption;
        this.discountRate = discountRate;
        this.isAvailable = isAvailable;
        this.isHidden = isHidden;
        this.isDeleted = isDeleted;
    }
}
