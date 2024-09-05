package com.hummingbird.kr.starbuckslike.auth.application;

import com.hummingbird.kr.starbuckslike.auth.domain.EmailAuthJob;
import com.hummingbird.kr.starbuckslike.auth.dto.*;
import com.hummingbird.kr.starbuckslike.auth.util.Cryptor;
import com.hummingbird.kr.starbuckslike.auth.util.EmailAuth;
import com.hummingbird.kr.starbuckslike.auth.util.JwtUtil;
import com.hummingbird.kr.starbuckslike.member.domain.Member;
import com.hummingbird.kr.starbuckslike.member.infrastructrue.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MemberRepository memberRepository;
    public PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(13);

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginreqDTO) {


        throw new RuntimeException("Invalid username or password");
    }

    @Override
    public Optional<Member> findByMemberUID(String memberUid) {
        return memberRepository.findByMemberUID(memberUid);
    }

    @Override
    public String sendAuthSMS(String phoneNumber) {
        return null;
    }

    @Override
    public boolean verifyAuthChallenge(EmailAuthJob authjob, String authChallenge) {
//        return emailAuth.AuthCheck(authjob, authChallenge);
        return false;
    }

    @Override
    public void verifyAuthSMS(String phoneNumber, String authSMS) {
    }

    public RegisterResponseDTO registerMember(RegisterRequestDTO registerRequestDTO) {
        Member m = new Member();
        m.setBirthdate(registerRequestDTO.getBirthdate());
        m.setName(registerRequestDTO.getName());
        m.setNickname(registerRequestDTO.getNickname());
        m.setPhone(registerRequestDTO.getPhone());
        m.setEmail(registerRequestDTO.getEmail());
        m.setLoginID(registerRequestDTO.getLoginID());
        m.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        m.setMemberUID(UUID.randomUUID().toString());
        memberRepository.save(m);
        return new RegisterResponseDTO();
    }

    @Override
    public void checkEmail(String email) {
    }

    @Override
    public void checkId(String loginID) {
    }

    @Override
    public void checkphone(String phone) {
    }

    @Override
    public String getSecret() {
        try {
            SecretKey secretKey = Cryptor.generateKey();
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void logout() {
    }

    @Override
    public void OAuthLogin(String loginID, String password) {
    }

    @Override
    public void OAuthRegister(String loginID, String email, String password, String name, String phone, String Nickname, LocalDate birth) {
    }

    @Override
    public ResetPWResponseDTO updatePassword(ResetPWRequestDTO resetPWreqDTO) {
        String newPassword = passwordEncoder.encode(resetPWreqDTO.getNewPW());
        memberRepository.updatePasswordByUuid(resetPWreqDTO.getMemberUID(), newPassword);
        return new ResetPWResponseDTO();
    }

    public Optional<Member> findMemberByUid(String memberUid) {
        return memberRepository.findByMemberUID(memberUid);
    }
}