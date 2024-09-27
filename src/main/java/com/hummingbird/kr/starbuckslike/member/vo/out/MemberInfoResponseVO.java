package com.hummingbird.kr.starbuckslike.member.vo.out;

import java.time.LocalDateTime;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Builder;

@Getter
public class MemberInfoResponseVO {
    
    private String loginID;
    private String name;
    private String nickname;
    private LocalDate birthDate;
    private String phone;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    @Builder
    public MemberInfoResponseVO(String loginID, String name, String nickname, LocalDate birthDate, String phone, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.loginID = loginID;
        this.name = name;
        this.nickname = nickname;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
