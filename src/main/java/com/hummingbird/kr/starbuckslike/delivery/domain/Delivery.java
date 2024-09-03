package com.hummingbird.kr.starbuckslike.delivery.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   //배송지 아이디

    @Column(name = "alias",length = 50)
    private String alias; //배송지 별칭

    @Column(name = "name",length = 20)
    private String name; //배송지 받는사람 이름

    @Column(name = "address",nullable = false)
    private String address; //배송지 기본주소 기입 우편번호 + 상세주소 + 기본주소 풀주소가 들어온다.

    @Column(name = "phone")  //@Column(name = " ") 필드와 매핑할 컬럼의 이름을 설정, 기본값 객체의 필드이름
    private int phone; //배송지연락처

    @Column(name = "user_uuid" , nullable = false, length = 100)
    private String userUid; // 유저아이디

    private String memo;


    @Column(name = "is_basic")
    @ColumnDefault("false")
    private Boolean isBasic = false;   // 기본주소지 등록할껀지 묻는 부분..

}
