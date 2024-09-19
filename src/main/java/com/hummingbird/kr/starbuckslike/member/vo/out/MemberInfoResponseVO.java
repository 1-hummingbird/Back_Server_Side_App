package com.hummingbird.kr.starbuckslike.member.vo.out;

import com.hummingbird.kr.starbuckslike.member.domain.MemberInfo;
import java.time.LocalDateTime;
import java.time.LocalDate;

import lombok.Getter;

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
    
    public MemberInfoResponseVO(MemberInfo memberInfo) {
        this.loginID = memberInfo.getLoginID();
        this.name = memberInfo.getName();
        this.nickname = memberInfo.getNickname();
        this.birthDate = memberInfo.getBirthdate();
        this.phone = memberInfo.getPhone();
        this.email = memberInfo.getEmail();
        this.createdAt = memberInfo.getCreatedAt();
        this.updatedAt = memberInfo.getUpdatedAt();
    }
}
