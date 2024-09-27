package com.hummingbird.kr.starbuckslike.member.dto.in;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import com.hummingbird.kr.starbuckslike.member.domain.Member;
import com.hummingbird.kr.starbuckslike.member.vo.in.MemberUpdateRequestVO;

@Setter
@Getter
@NoArgsConstructor
public class MemberUpdateRequestDTO {

    private String memberUID;
    private String name;
    private String nickname;
    private LocalDate birthdate;
    private String phone;
    private String email;

    public Member toEntity(Member oldEntity) {
        return Member.builder()
            .id(oldEntity.getId())
            .name(this.name != null ? this.name : oldEntity.getName())
            .nickname(this.nickname != null ? this.nickname : oldEntity.getNickname())
            .birthdate(this.birthdate != null ? this.birthdate : oldEntity.getBirthdate())
            .phone(this.phone != null ? this.phone : oldEntity.getPhone())

            .email(this.email != null ? this.email : oldEntity.getEmail())
            .memberUID(oldEntity.getMemberUID())
            .loginID(oldEntity.getLoginID())
            .password(oldEntity.getPassword())
            .build();
    }

    @Builder
    public MemberUpdateRequestDTO(String memberUID, String name, String nickname, LocalDate birthdate, String phone, String email) {
        this.memberUID = memberUID;
        this.name = name;
        this.nickname = nickname;
        this.birthdate = birthdate;
        this.phone = phone;
        this.email = email;
    }

    public static MemberUpdateRequestDTO of(MemberUpdateRequestVO memberUpdateRequestVO, String memberUID) {
        return MemberUpdateRequestDTO.builder()
            .memberUID(memberUID)
            .name(memberUpdateRequestVO.getName())
            .nickname(memberUpdateRequestVO.getNickname())
            .birthdate(memberUpdateRequestVO.getBirthDate())
            .phone(memberUpdateRequestVO.getPhone())
            .email(memberUpdateRequestVO.getEmail())
            .build();
    }
}
