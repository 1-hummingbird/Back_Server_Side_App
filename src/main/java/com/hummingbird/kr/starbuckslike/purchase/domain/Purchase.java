package com.hummingbird.kr.starbuckslike.purchase.domain;

import com.hummingbird.kr.starbuckslike.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Purchase extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Comment("총 결제금액")
    @Column
    private Long totalPrice;

    @Comment("총 할인 금액")
    @Column
    private Long totalDiscount;

    @Comment("받는 주소지")
    @Column(name = "address" , length = 200 , nullable = false)
    private String address;

    @Comment("전화번호 1 (필수)")
    @Column(name="primary_phone",length = 30, nullable = false)
    private String primaryPhone;

    @Comment("전화번호 2")
    @Column(name="secondary_phone",length = 30)
    private String secondaryPhone;

    @Comment("받는사람 이름")
    @Column(name = "user_name" , length = 80 , nullable = false)
    private String userName;

    @Comment("회원 uuid")
    @Column(name = "user_uuid" , nullable = false, length = 100)
    private String userUuid;

    @Comment("배송 요청사항")
    @Column(name = "memo" , length = 150)
    private String memo;

    @Comment("삭제 여부")
    @Column(nullable = false)
    private Boolean isDelete;


    @Builder
    public Purchase(Long totalPrice, Long totalDiscount, String address, String primaryPhone, String secondaryPhone,
                    String userName, String userUuid, String memo, Boolean isDelete) {
        this.totalPrice = totalPrice;
        this.totalDiscount = totalDiscount;
        this.address = address;
        this.primaryPhone = primaryPhone;
        this.secondaryPhone = secondaryPhone;
        this.userName = userName;
        this.userUuid = userUuid;
        this.memo = memo;
        this.isDelete = isDelete;
    }
}
