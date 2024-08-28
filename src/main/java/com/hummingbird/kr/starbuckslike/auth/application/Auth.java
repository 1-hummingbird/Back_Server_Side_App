package com.hummingbird.kr.starbuckslike.auth.application;

import com.hummingbird.kr.starbuckslike.auth.domain.EmailAuthJob;

import java.time.LocalDateTime;
import java.util.Random;

// todo : 이게  빈으로 가던지 컨텍스트 내부로 던져야 할 거 같아요
public class Auth {
    private String serviceProviderEmail = "";
    private String serviceProviderPassword = "";
    public EmailAuthJob AuthStart(String userEmail) {
        String authChallenge = generateAuthCode();
        EmailAuthJob EmailAuthJob = new EmailAuthJob(userEmail, authChallenge, LocalDateTime.now().plusMinutes(5));
        authChallenge = "your authentication code is: " + authChallenge;
        EmailSender.sendEmail(userEmail, "Authentication Code", authChallenge, serviceProviderEmail, serviceProviderPassword);
        return EmailAuthJob;
    }
    public boolean AuthCheck(EmailAuthJob EmailAuthJob, String authCode) {
        return LocalDateTime.now().isBefore(EmailAuthJob.getExpireTime()) && EmailAuthJob.getAuthcode().equals(authCode);
    }

    public void setProviderEmail(String email) {
        this.serviceProviderEmail = email;
    }
    public void setProviderPassword(String password) {
        this.serviceProviderPassword = password;
    }

    public Auth(String ServiceMail, String ServicePW){
        this.serviceProviderEmail = ServiceMail;
        this.serviceProviderPassword = ServicePW;
    }
    public static String generateAuthCode() {
        // Generate a random 7-digit authentication code
        Random random = new Random();
        int randomNumber;
        randomNumber = 10000000 + random.nextInt(90000000);
        return "S" + String.valueOf(randomNumber);
    }
}

