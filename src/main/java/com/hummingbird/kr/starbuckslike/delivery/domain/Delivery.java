package com.hummingbird.kr.starbuckslike.delivery.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   //배송지 아이디

    @Column(name = "alias", length = 50, nullable = false)
    private String alias; //배송지 별칭

    @Column(name = "name", length = 20, nullable = false)
    private String name; //배송지 받는사람 이름

    @Column(name = "address",length = 100, nullable = false)
    private String address; //배송지 기본주소 기입 우편번호 + 상세주소 + 기본주소 풀주소가 들어온다.

    @Column(name = "phone", length = 30, nullable = false)  //@Column(name = " ") 필드와 매핑할 컬럼의 이름을 설정, 기본값 객체의 필드이름
    private String phone; //배송지연락처  //연락처 하나더 추가하기.

    @Column(name = "user_uuid" , nullable = false, length = 100)
    private String userUuid; // 유저아이디

    @Column(length = 200, nullable = true)
    private String memo;


    @Column(name = "is_basic")
    @ColumnDefault("false")
    private Boolean isBasic;   // 기본주소지 등록할껀지 묻는 부분..


    @Builder //생성자.
    public Delivery(Long id, String alias, String name, String address, String phone, String userUuid, String memo, Boolean isBasic) {
        this.id = id;
        this.alias = alias;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.userUuid = userUuid;
        this.memo = memo;
        this.isBasic = isBasic;
    }
}
