package com.hummingbird.kr.starbuckslike.member.vo.in;

import lombok.Getter;

import java.util.Date;
import java.time.LocalDateTime;

import org.springframework.lang.Nullable;

import com.hummingbird.kr.starbuckslike.member.dto.in.FindMemberRequestDTO;

@Getter
public class FindMemberRequestVO {
    
    // if admin want to find someone birth in specific date
    private @Nullable Date birthdate;

    // if admin want to find someone birth in specific month, day, year
    private @Nullable Integer month;
    private @Nullable Integer day;
    private @Nullable Integer year;

    // if admin want to find someone with specific name or nickname
    private @Nullable String name;
    private @Nullable String nickname;

    // if admin want to find someone with specific register date or update date
    private @Nullable LocalDateTime registerBiginDate;
    private @Nullable LocalDateTime registerEndDate;

    private @Nullable LocalDateTime updateBiginDate;
    private @Nullable LocalDateTime updateEndDate;

    // if admin want to find someone with specific memberUID
    // this parameter is for following situation
    // 1. when admin want to find who written some review
    // 2. when admin want to find who purchased some product or purchase
    // 3. and so on
    private @Nullable String memberUID;

    // if admin want to find someone with specific email or phone
    // this caused by some external contact has issue like bad action or something
    private @Nullable String email;
    private @Nullable String phone;

    public FindMemberRequestDTO toDTO() {
        return FindMemberRequestDTO.builder()
                .birthdate(birthdate)
                .month(month)
                .day(day)
                .year(year)
                .name(name)
                .nickname(nickname)
                .registerBiginDate(registerBiginDate)
                .registerEndDate(registerEndDate)
                .updateBiginDate(updateBiginDate)
                .updateEndDate(updateEndDate)
                .memberUID(memberUID)
                .email(email)
                .phone(phone)
                .build();
    }
    
}
