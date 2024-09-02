package com.hummingbird.kr.starbuckslike.delivery.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;   //배송지 아이디

    @Column(name = "alias")
    private String alias; //배송지 별칭

    @Column(name = "name")
    private String name; //배송지 받는사람 이름

    @Column(name = "address")
    private String address; //배송지 기본주소 기입

    @Column(name = "phone")  //@Column(name = " ") 필드와 매핑할 컬럼의 이름을 설정, 기본값 객체의 필드이름
    private int phone; //배송지연락처

    private String userid; // 유저아이디

 //   private String basic_flag;   // 기본주소지 등록할껀지 묻는 부분..

}
