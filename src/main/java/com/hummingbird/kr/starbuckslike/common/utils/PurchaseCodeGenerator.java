package com.hummingbird.kr.starbuckslike.common.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class PurchaseCodeGenerator {
    public static String generateOrderCode() {
        // 오늘 날짜 8자리
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        // 랜덤 10자리 숫자
        Random random = new Random();
        long randomNumber = 1_000_000_000L + (long)(random.nextDouble() * 9_000_000_000L);
        // 날짜 + 랜덤 숫자를 문자열로 반환
        return currentDate + randomNumber;
    }

}
