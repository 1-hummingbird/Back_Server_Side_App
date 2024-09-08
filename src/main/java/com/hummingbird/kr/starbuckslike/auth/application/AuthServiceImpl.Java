package com.hummingbird.kr.starbuckslike.auth.application;

import com.hummingbird.kr.starbuckslike.auth.domain.EmailAuthJob;
import com.hummingbird.kr.starbuckslike.auth.dto.*;
import com.hummingbird.kr.starbuckslike.auth.infrastucture.AuthRepository;
import com.hummingbird.kr.starbuckslike.auth.util.Cryptor;
import com.hummingbird.kr.starbuckslike.member.domain.Member;


import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
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

    private final PasswordEncoder passwordEncoder;
    private final AuthRepository authRepository;
    private final CustomAuthenticationProvider customAuthenticationProvider;
    private final JwtUtil jwtUtil;

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginreqDTO) {
        try {
            customAuthenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(loginreqDTO.getLoginID(), loginreqDTO.getPassword())
            );
            Optional<Member> memberOptional = authRepository.findByid(loginreqDTO.getLoginID());
            if (memberOptional.isPresent()) {
                Member member = memberOptional.get();
                String token = jwtUtil.issueJwt(member.getMemberUID(), false);
                return new LoginResponseDTO(token);
            }
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid username or password");
        }
        throw new RuntimeException("Invalid username or password");
    }

    public SecondLoginResponseDTO SecondLogin(SecondLoginRequestDTO login2ReqDTO){
        try {
            customAuthenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(login2ReqDTO.getLoginID(), login2ReqDTO.getPassword())
            );
            Optional<Member> memberOptional = authRepository.findByid(login2ReqDTO.getLoginID());
            if (memberOptional.isPresent()) {
                Member member = memberOptional.get();
                String token = jwtUtil.issueJwt(member.getMemberUID(), true);
                return new SecondLoginResponseDTO(token);
            }
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid username or password");
        }
        throw new RuntimeException("Invalid username or password");
    }

    @Override
    public EmailAuthJob sendAuthChallenge(String email) {
        return null;
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
        authRepository.save(m);
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
        authRepository.updatePasswordByUuid(resetPWreqDTO.getMemberUID(), newPassword);
        return new ResetPWResponseDTO();
    }

    public Optional<Member> findMemberByUid(String memberUid) {
        return authRepository.findByMemberUID(memberUid);
    }
}