package com.hummingbird.kr.starbuckslike.mail.application;

public interface MailService {

    void sendMail(String to, String subject, String text);

}
