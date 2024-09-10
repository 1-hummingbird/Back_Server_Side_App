package com.hummingbird.kr.starbuckslike.member.domain;

import com.hummingbird.kr.starbuckslike.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import java.util.Date;

@Setter
@Getter
@Slf4j
@Entity
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String loginID;

    @Column(nullable = false, length = 40)
    private String name;

    @Comment("회원 닉네임")
    @Column(length = 40)
    private String nickname;

    @Comment("회원 생년월일")
    @Column(nullable = false)
    private Date birthdate;

    @Comment("회원 전화번호")
    @Column(nullable = false, length = 20)
    private String phone;

    @Comment("회원 이메일")
    @Column(nullable = false, length = 100)
    private String email;

    @Comment("회원 비밀번호")
    @Column(nullable = false, length = 85)
    private String password;

    @Comment("삭제 여부")
    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean isDeleted = false;

    @Comment("회원 UUID")
    @Column(nullable = false)
    private String memberUID;

    @Builder
    public Member(String loginId, String phone, String email, String password, String name, String nickName, Date birth, Boolean isDeleted) {
        this.loginID = loginId;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickName;
        this.birthdate = birth;
        this.isDeleted = isDeleted;
    }

    public Member(Long id, String loginId, String phone, String email, String password, String name, String nickName, Date birth, Boolean isDeleted) {
        this.id = id;
        this.loginID = loginId;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickName;
        this.birthdate = birth;
        this.isDeleted = isDeleted;
    }

}
