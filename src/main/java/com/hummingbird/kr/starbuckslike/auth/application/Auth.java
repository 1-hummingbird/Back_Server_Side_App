package com.hummingbird.kr.starbuckslike.auth.application;

import java.time.LocalDateTime;
import java.util.Random;

class Authenticating {
    public static String generateAuthCode() {
        // Generate a random 7-digit authentication code
        Random random = new Random();
        int randomNumber;
        randomNumber = 10000000 + random.nextInt(90000000);
        return "S" + String.valueOf(randomNumber);
    }
}
// todo : 이게  빈으로 가던지 컨텍스트 내부로 던져야 할 거 같아요
public class Auth {
    private String serviceProviderEmail = "";
    private String serviceProviderPassword = "";
    public AuthJob AuthStart(String userEmail) {
        String authChallenge = Authenticating.generateAuthCode();
        AuthJob AuthJob = new AuthJob(userEmail, authChallenge, LocalDateTime.now().plusMinutes(5));
        authChallenge = "your authentication code is: " + authChallenge;
        EmailSender.sendEmail(userEmail, "Authentication Code", authChallenge, serviceProviderEmail, serviceProviderPassword);
        return AuthJob;
    }
    public boolean AuthCheck(AuthJob AuthJob, String authCode) {
        return LocalDateTime.now().isBefore(AuthJob.getExpireTime()) && AuthJob.getAuthcode().equals(authCode);
    }
    public void setProviderEmail(String email) {
        this.serviceProviderEmail = email;
    }
    public void setProviderPassword(String password) {
        this.serviceProviderPassword = password;
    }
}

