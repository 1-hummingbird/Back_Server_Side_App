package com.hummingbird.kr.starbuckslike.member.dto.in;

import org.springframework.lang.Nullable;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.time.LocalDateTime;

@Getter
@Builder
public class FindMemberRequestDTO {

    private String requesterUID;

    private @Nullable Date birthdate;

    private @Nullable Integer month;
    private @Nullable Integer day;
    private @Nullable Integer year;

    private @Nullable String name;
    private @Nullable String nickname;

    private @Nullable LocalDateTime registerBiginDate;
    private @Nullable LocalDateTime registerEndDate;

    private @Nullable LocalDateTime updateBiginDate;
    private @Nullable LocalDateTime updateEndDate;

    private @Nullable String memberUID;

    private @Nullable String email;
    private @Nullable String phone;


}
