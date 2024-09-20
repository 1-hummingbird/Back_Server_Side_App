package com.hummingbird.kr.starbuckslike.shipping.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;


@Entity
@Getter
@NoArgsConstructor
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
    private String primaryPhone;

    @Column(length = 30)
    private String secondaryPhone;

    @Column(length = 40, nullable = false)
    private String memberUID;

    @Column(length = 200)
    private String memo;

    @Column(name = "default_address", nullable = false)
    private Boolean defaultAddress = false;

    @Builder
    public ShippingAddress(
                           Long id,
                           String addressNickname,
                           String name,
                           String address,
                           String primaryPhone,
                           String secondaryPhone,
                           String memberUID,
                           String memo,
                           Boolean defaultAddress
    ) {
        this.id = id;
        this.addressNickname = addressNickname;
        this.name = name;
        this.address = address;
        this.primaryPhone = primaryPhone;
        this.secondaryPhone = secondaryPhone;
        this.memberUID = memberUID;
        this.memo = memo;
        this.defaultAddress = defaultAddress;
    }
}

