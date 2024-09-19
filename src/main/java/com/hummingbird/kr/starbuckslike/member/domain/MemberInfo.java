package com.hummingbird.kr.starbuckslike.member.domain;

import java.time.LocalDateTime;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberInfo {

    private String loginID;
    private String name;
    private String nickname;
    private LocalDate birthdate;
    private String phone;
    private String email;
    private String memberUID;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public MemberInfo(Member member) {
        this.loginID = member.getLoginID();
        this.name = member.getName();
        this.nickname = member.getNickname();
        this.birthdate = member.getBirthdate();
        this.phone = member.getPhone();
        this.email = member.getEmail();
        this.memberUID = member.getMemberUID();
        this.createdAt = member.getCreatedAt();
        this.updatedAt = member.getUpdatedAt();
    }

}
