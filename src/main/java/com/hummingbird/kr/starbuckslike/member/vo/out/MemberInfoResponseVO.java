package com.hummingbird.kr.starbuckslike.member.vo.out;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Getter;

@Getter
public class MemberInfoResponseVO {
    private String loginID;
    private String name;
    private String nickname;
    private Date birthDate;
    private String phone;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public MemberInfoResponseVO(String loginID, String name, String nickname, Date birthDate, String phone, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
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
