package com.hummingbird.kr.starbuckslike.auth.application;

import com.hummingbird.kr.starbuckslike.auth.domain.EmailAuthJob;
import com.hummingbird.kr.starbuckslike.auth.dto.LoginRequestDTO;
import com.hummingbird.kr.starbuckslike.auth.dto.LoginResponseDTO;
import com.hummingbird.kr.starbuckslike.member.domain.Member;

import com.hummingbird.kr.starbuckslike.member.dto.MemberDTO;
import com.hummingbird.kr.starbuckslike.member.infrastructrue.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private MemberRepository memberRepository;

    // todo : get service Mail and PW from env file
    private final String serviceMail = "";
    private final String servicePW = "";
    private final Auth auth = new Auth(serviceMail, servicePW);
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public EmailAuthJob sendAuthChallenge(String email) {
        return auth.AuthStart(email);
    }

    @Override
    public String sendAuthSMS(String phoneNumber) {
        return null;
    }

    @Override
    public boolean verifyAuthChallenge(EmailAuthJob authjob, String authChallenge) {
        return auth.AuthCheck(authjob, authChallenge);
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

        Optional<Member> memberOpt = memberRepository.findByid(loginid);
        if (!memberOpt.isPresent()) {
            // Handle member not found case
            throw new RuntimeException("Member not found");
        }
        Member member = memberOpt.get();
        String inDBpassword = member.getPassword();
        boolean passed = passwordEncoder.matches(password, inDBpassword);
        return new LoginResponseDTO();
    }

    public void sendSecret(){
        try {
            SecretKey secretKey = Cryptor.generateKey();
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
        memberRepository.updatePasswordByUuid(uuid, newPassword);
    }
}