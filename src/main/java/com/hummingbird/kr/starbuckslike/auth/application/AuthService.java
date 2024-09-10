//package com.hummingbird.kr.starbuckslike.auth.application;
//
//import com.hummingbird.kr.starbuckslike.auth.domain.EmailAuthJob;
//import com.hummingbird.kr.starbuckslike.auth.dto.LoginRequestDTO;
//import com.hummingbird.kr.starbuckslike.auth.dto.LoginResponseDTO;
//
//import java.time.LocalDate;
//
//public interface AuthService {
//
//
//    EmailAuthJob sendAuthChallenge(String email);
//
//    String sendAuthSMS(String phoneNumber);
//
//    boolean verifyAuthChallenge(EmailAuthJob authjob, String authChallenge);
//
//    void verifyAuthSMS(String phoneNumber, String authSMS);
//
//
//    void registerMember(String loginID, String email, String password, String name, String mainphone, String Nickname, LocalDate birth);
//
//    void checkEmail(String email);
//
//    void checkId(String loginID);
//
//    void checkphone(String mainphone);
//
//
//    LoginResponseDTO login(LoginRequestDTO loginreqDTO);
//
//    void logout();
//
//
//    void OAuthLogin(String loginID, String password);
//
//    void OAuthRegister(String loginID, String email, String password, String name, String mainphone, String Nickname, LocalDate birth);
//
//    void updatePassword(String uuid, String newPassword);
//
//    String getSecret();
//}
