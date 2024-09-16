package com.hummingbird.kr.starbuckslike.member.dto.in;

import java.util.Date;

import lombok.Getter;

@Getter
public class MemberUpdateRequestDTO {
    private String memberUID;
    private String name;
    private String nickname;
    private Date birthDate;
    private String phone;
    private String email;

    public MemberUpdateRequestDTO(String memberUID, String name, String nickname, Date birthDate, String phone, String email) {
        this.memberUID = memberUID;
        this.name = name;
        this.nickname = nickname;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
    }
}
