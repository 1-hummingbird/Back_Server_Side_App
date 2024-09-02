package com.hummingbird.kr.starbuckslike.auth.util;

class EmailSenderTest {
    public static void main(String[] args) {
        EmailSender emailSender = new EmailSender();
        emailSender.sendEmail("yuseok.kim@edushare.kr","test","테스트에용",
        "kr.teamhummingbird@gmail.com","qjftosms!sksek0");
    }
}