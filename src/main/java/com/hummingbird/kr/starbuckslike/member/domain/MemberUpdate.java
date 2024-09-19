package com.hummingbird.kr.starbuckslike.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class MemberUpdate {
    private Long memberId;
    private String loginID;
    private String name;
    private String nickname;
    private LocalDate birthdate;
    private String phone;
    private String email;
    private String password;
    private Boolean isDeleted;
    private String memberUID;

}
