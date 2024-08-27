package com.hummingbird.kr.starbuckslike.auth.service;

import java.util.Random;

public static String generateAuthCode() {
    // Generate a random 7-digit authentication code
    Random random = new Random();
    int randomNumber;
    randomNumber = 10000000 + random.nextInt(90000000);
    return "S" + String.valueOf(randomNumber);
}

public class MemberAuthService implements AuthService {

    @Override
    public String sendAuthChallenge(String email) {
        return null;
    }

    @Override
    public String sendAuthSMS(String phoneNumber) {
        return null;
    }

    @Override
    public void verifyAuthChallenge(String email, String authChallenge) {

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