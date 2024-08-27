package com.hummingbird.kr.starbuckslike.auth.service;

import com.hummingbird.kr.starbuckslike.auth.application.AuthJob;
import com.hummingbird.kr.starbuckslike.auth.application.EmailSender;
import com.hummingbird.kr.starbuckslike.auth.application.Auth;

private final Auth auth;

// todo : 환경 설정에서 가져오도록 변경
auth.setProviderEmail("serviceProviderEmail");
auth.setProviderPassword("serviceProviderPassword");

public class AuthServiceImpl implements AuthService {

    @Override
    public AuthJob sendAuthChallenge(String email) {
        return auth.AuthStart(email);
    }

    @Override
    public String sendAuthSMS(String phoneNumber) {
        return null;
    }

    @Override
    public boolean verifyAuthChallenge(Authjob authjob, String authChallenge) {
        return auth.AuthCheck(authjob, authChallenge);
    }

    @Override
    public void verifyAuthSMS(String phoneNumber, String authSMS) {

    }

    @Override
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
    public void login(String loginID, String password) {

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
    public void restetPassword(String loginID, String email) {

    }
}