package com.hummingbird.kr.starbuckslike.auth.application;

import com.hummingbird.kr.starbuckslike.auth.domain.EmailAuthJob;
import com.hummingbird.kr.starbuckslike.auth.dto.*;

import java.time.LocalDate;

public interface AuthService {


    EmailAuthJob sendAuthChallenge(String email);

    String sendAuthSMS(String phoneNumber);

    boolean verifyAuthChallenge(EmailAuthJob authjob, String authChallenge);

    void verifyAuthSMS(String phoneNumber, String authSMS);


    RegisterResponseDTO registerMember(RegisterRequestDTO registerRequestDTO);

    void checkEmail(String email);

    void checkId(String loginID);

    void checkphone(String phone);


    LoginResponseDTO login(LoginRequestDTO loginreqDTO);

    void logout();
    

    void OAuthLogin(String loginID, String password);

    void OAuthRegister(String loginID, String email, String password, String name, String phone, String Nickname, LocalDate birth);

    ResetPWResponseDTO updatePassword(ResetPWRequestDTO resetPWreqDTO);

    String getSecret();
}
