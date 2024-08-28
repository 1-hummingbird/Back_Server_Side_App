package com.hummingbird.kr.starbuckslike.auth.application;

import java.time.LocalDate;

public class AuthServiceImpl implements AuthService {

    // todo : get service Mail and PW from env file
    private final String serviceMail = "";
    private final String servicePW = "";
    private final Auth auth = new Auth(serviceMail, servicePW);

    @Override
    public AuthJob sendAuthChallenge(String email) {
        return auth.AuthStart(email);
    }

    @Override
    public String sendAuthSMS(String phoneNumber) {
        return null;
    }

    @Override
    public boolean verifyAuthChallenge(AuthJob authjob, String authChallenge) {
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