package com.hummingbird.kr.starbuckslike.auth.service;

import java.time.LocalDate;

public interface AuthService {


    String sendAuthChallenge(String email);

    String sendAuthSMS(String phoneNumber);

    void verifyAuthChallenge(String email, String authChallenge);

    void verifyAuthSMS(String phoneNumber, String authSMS);


    void registerMember(String loginID, String email, String password, String name, String phone, String Nickname, LocalDate birth);

    void checkEmail(String email);

    void checkId(String loginID);

    void checkphone(String phone);


    void login(String loginID, String password);

    void logout();
    

    void OAuthLogin(String loginID, String password);

    void OAuthRegister(String loginID, String email, String password, String name, String phone, String Nickname, LocalDate birth);


    void restetPassword(String loginID, String email);
}
