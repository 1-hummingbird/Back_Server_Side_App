package com.hummingbird.kr.starbuckslike.temp.domain;

import com.hummingbird.kr.starbuckslike.temp.domain.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.ColumnDefault;

public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "login_id" ,nullable = false)
    private String loginId; // 로그인 id

    @Column(name = "phone" , length = 20)
    private String phone;

    @Column(name = "email" , length = 100)
    private String email;

    @Column(name = "password" , nullable = false)
    private String password;

    @Column(name = "name" , nullable = false , length = 50)
    private String name;

    @Column(name = "nickname" , length = 50)
    private String nickName;

    @Column(name = "birth" , length = 12)
    private String birth; // 생년월일 "1998-06-12" 이런식으로 저장될거임

    @Column(name = "is_deleted")
    @ColumnDefault("false")
    private Boolean isDeleted = false; // 회원 탈퇴 여부



}
