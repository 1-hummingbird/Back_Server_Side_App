package com.hummingbird.kr.starbuckslike.member.dto.out;

import com.hummingbird.kr.starbuckslike.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.hummingbird.kr.starbuckslike.member.vo.out.MemberInfoResponseVO;

@Getter
public class MemberInfoResponseDTO {
    
    private final String loginID;
    private final String name;
    private final String nickname;
    private final LocalDate birthdate;
    private final String phone;
    private final String email;
    private final String password;
    private final String memberUID;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    @Builder
    public MemberInfoResponseDTO(String loginID, String name, String nickname, LocalDate birthdate, String phone, String email, String password, String memberUID, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.loginID = loginID;
        this.name = name;
        this.nickname = nickname;
        this.birthdate = birthdate;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.memberUID = memberUID;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static MemberInfoResponseDTO from(Member member) {
        return MemberInfoResponseDTO.builder()
            .loginID(member.getLoginID())
            .name(member.getName())
            .nickname(member.getNickname())
            .birthdate(member.getBirthdate())
            .phone(member.getPhone())
            .email(member.getEmail())
            .password(member.getPassword())
            .memberUID(member.getMemberUID())
            .createdAt(member.getCreatedAt())
            .updatedAt(member.getUpdatedAt())
            .build();
    }

    public static MemberInfoResponseVO toVO(MemberInfoResponseDTO dto) {
        return MemberInfoResponseVO.builder()
            .loginID(dto.getLoginID())
            .name(dto.getName())
            .nickname(dto.getNickname())
            .birthDate(dto.getBirthdate())
            .phone(dto.getPhone())
            .email(dto.getEmail())
            .createdAt(dto.getCreatedAt())
            .updatedAt(dto.getUpdatedAt())
            .build();
    }
}
