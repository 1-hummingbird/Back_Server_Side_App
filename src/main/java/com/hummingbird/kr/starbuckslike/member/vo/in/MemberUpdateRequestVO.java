package com.hummingbird.kr.starbuckslike.member.vo.in;

import com.hummingbird.kr.starbuckslike.member.dto.in.MemberUpdateRequestDTO;

import java.util.Date;
import lombok.Getter;

@Getter
public class MemberUpdateRequestVO {
    private String memberUID;
    private String name;
    private String nickname;
    private Date birthDate;
    private String phone;
    private String email;

    public MemberUpdateRequestDTO toDTO() {
        return new MemberUpdateRequestDTO(memberUID, name, nickname, birthDate, phone, email);
    }
}
