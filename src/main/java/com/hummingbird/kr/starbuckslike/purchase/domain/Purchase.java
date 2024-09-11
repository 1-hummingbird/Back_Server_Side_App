package com.hummingbird.kr.starbuckslike.purchase.domain;

import com.hummingbird.kr.starbuckslike.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Purchase extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "address" , length = 200 , nullable = false)
    private String address; // 받는 주소지

    @Column(name="primary_phone",length = 30, nullable = false)
    private String primaryPhone; // 전화번호 1 (필수)

    @Column(name="secondary_phone",length = 30)
    private String secondaryPhone; // 전화번호 2

    @Column(name = "user_name" , length = 80 , nullable = false)
    private String userName; // 주문자 이름

    @Column(name = "user_uuid" , nullable = false, length = 100)
    private String userUuid;

    @Column(name = "memo" , length = 200)
    private String memo; // 배송 요청사항

    @Builder
    public Purchase(String address, String primaryPhone, String secondaryPhone,
                    String userName, String userUuid, String memo) {
        this.address = address;
        this.primaryPhone = primaryPhone;
        this.secondaryPhone = secondaryPhone;
        this.userName = userName;
        this.userUuid = userUuid;
        this.memo = memo;
    }
}
