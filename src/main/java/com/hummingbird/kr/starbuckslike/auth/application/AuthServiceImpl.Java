package com.hummingbird.kr.starbuckslike.auth.application;

import com.hummingbird.kr.starbuckslike.auth.domain.EmailAuthJob;
import com.hummingbird.kr.starbuckslike.auth.domain.CustomUserDetails;
import com.hummingbird.kr.starbuckslike.auth.dto.LoginRequestDTO;
import com.hummingbird.kr.starbuckslike.auth.dto.LoginResponseDTO;
import com.hummingbird.kr.starbuckslike.auth.util.Cryptor;
import com.hummingbird.kr.starbuckslike.auth.util.EmailAuth;
import com.hummingbird.kr.starbuckslike.auth.infrastructure.AuthRepository;

import com.hummingbird.kr.starbuckslike.auth.util.JwtTokenProvider;
import com.hummingbird.kr.starbuckslike.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private AuthRepository memberRepository;

    // todo : get service Mail and PW from env file
    private final String serviceMail = "";
    private final String servicePW = "";
    private final EmailAuth emailAuth = new EmailAuth(serviceMail, servicePW);
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    @Override
    public EmailAuthJob sendAuthChallenge(String email) {
        return emailAuth.AuthStart(email);
    }

    @Override
    public String sendAuthSMS(String phoneNumber) {
        return null;
    }

    @Override
    public boolean verifyAuthChallenge(EmailAuthJob authjob, String authChallenge) {
        return emailAuth.AuthCheck(authjob, authChallenge);
    }

    @Override
    public void verifyAuthSMS(String phoneNumber, String authSMS) {

    }

    public void registerMember(String loginID, String email, String password, String name, String phone, String Nickname, LocalDate birth) {

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
    public LoginResponseDTO login(LoginRequestDTO loginreqDTO) {
        String loginid = loginreqDTO.getLoginID();
        String password = loginreqDTO.getPassword();

        try{
            String token = createToken(authenticate(memberRepository.findByid(loginid),password));
            return new LoginResponseDTO(token);
        } catch (Exception e) {throw new RuntimeException(e);}


    }

    @Override
    public String getSecret(){
        try {
            SecretKey secretKey = Cryptor.generateKey();
            String SendingKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
            return SendingKey;
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
    public void updatePassword(String uuid, String newPassword) {
        newPassword = passwordEncoder.encode(newPassword);
        memberRepository.updatePasswordByUuid(uuid, newPassword);
    }

    private String createToken(Authentication authentication) {
        return jwtTokenProvider.generateAccessToken(authentication);
    }

    private Authentication authenticate(Member member, String inputPassword) {
        CustomUserDetails authUserDetail = new CustomUserDetails(member);
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authUserDetail.getUsername(),
                        inputPassword
                )
        );
    }
}