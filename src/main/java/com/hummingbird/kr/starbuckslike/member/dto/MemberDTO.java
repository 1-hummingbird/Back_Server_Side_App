package com.hummingbird.kr.starbuckslike.member.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private Long id;
    private String loginID;
    private String name;
    private String nickname;
    private String birthdate;
    private String phone;
    private String email;
    private String password;
    private Boolean isDeleted;
    private String memberUID;

}
