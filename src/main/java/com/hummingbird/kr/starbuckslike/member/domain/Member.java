package com.hummingbird.kr.starbuckslike.member.domain;

import com.hummingbird.kr.starbuckslike.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

@Getter
@Slf4j
@Entity
@Table(name = "member")
@Builder
@AllArgsConstructor
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
    @Column( length = 12)
    private String birthdate;

    @Comment("회원 전화번호")
    @Column(nullable = false, length = 20)
    private String phone;

    @Comment("회원 이메일")
    @Column(nullable = false, length = 100)
    private String email;

    @Comment("회원 비밀번호")
    @Column(nullable = false)
    private String password;

    @Comment("삭제 여부")
    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean isDeleted = false;

    @Comment("회원 UUID")
    @Column(nullable = false)
    private String memberUID;


//    public Member(Long id, String loginId, String mainphone, String email, String password, String name, String nickName, String birth, Boolean isDeleted) {
//        this.id = id;
//        this.loginID = loginId;
//        this.mainphone = mainphone;
//        this.email = email;
//        this.password = password;
//        this.name = name;
//        this.nickname = nickName;
//        this.birthdate = birth;
//        this.isDeleted = isDeleted;
//    }

    protected Member() {
    }

}
