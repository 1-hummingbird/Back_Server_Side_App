package com.hummingbird.kr.starbuckslike.auth.dto.in;

import com.hummingbird.kr.starbuckslike.member.domain.Member;
import com.hummingbird.kr.starbuckslike.auth.vo.in.RegisterRequestVO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.UUID;

@Getter
@NoArgsConstructor
@ToString
public class RegisterRequestDTO {

    private String email;
    private String loginID;
    private String password;
    private String name;
    private String phone;
    private String nickname;
    private Date birth;
    private String address;


    public Member toEntity(PasswordEncoder passwordEncoder) {

        return Member.builder()
                .email(email)
                .loginID(loginID)
                .password(passwordEncoder.encode(password))
                .memberUID(UUID.randomUUID().toString())
                .name(name)
                .phone(phone)
                .nickname(nickname)
                .birthdate(birth)
                .build();
    }

    @Builder
    public RegisterRequestDTO(
            String email,
            String loginID,
            String password,
            String name,
            String phone,
            String nickname,
            Date birth
    ) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.nickname = nickname;
        this.birth = birth;
        this.loginID = loginID;
    }

    public static RegisterRequestDTO from(RegisterRequestVO registerRequestVO) {
        return RegisterRequestDTO.builder()
                .email(registerRequestVO.getEmail())
                .loginID(registerRequestVO.getLoginID())
                .password(registerRequestVO.getPassword())
                .name(registerRequestVO.getName())
                .phone(registerRequestVO.getPhone())
                .nickname(registerRequestVO.getNickname())
                .birth(registerRequestVO.getBirthdate())
                .build();
    }
}
