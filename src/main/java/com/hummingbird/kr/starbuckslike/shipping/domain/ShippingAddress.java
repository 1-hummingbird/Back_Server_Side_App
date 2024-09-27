package com.hummingbird.kr.starbuckslike.shipping.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("배송지 별칭")
    @Column(length = 50, nullable = false)
    private String addressNickname;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String address;

    @Column(length = 30, nullable = false)
    private String Phone;

    @Column(length = 40, nullable = false)
    private String memberUID;

    @Column(length = 200)
    private String memo;

    @Builder
    public ShippingAddress(
                           Long id,
                           String addressNickname,
                           String name,
                           String address,
                           String Phone,
                           String memberUID,
                           String memo
    ) {
        this.id = id;
        this.addressNickname = addressNickname;
        this.name = name;
        this.address = address;
        this.Phone = Phone;
        this.memberUID = memberUID;
        this.memo = memo;
    }

    public ShippingAddress(
            String addressNickname,
            String name,
            String address,
            String Phone,
            String memberUID,
            String memo
    ) {
        this.addressNickname = addressNickname;
        this.name = name;
        this.address = address;
        this.Phone = Phone;
        this.memberUID = memberUID;
        this.memo = memo;
    }
}

