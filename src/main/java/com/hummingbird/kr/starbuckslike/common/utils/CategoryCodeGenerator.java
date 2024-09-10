package com.hummingbird.kr.starbuckslike.common.utils;

import java.util.UUID;

public class CategoryCodeGenerator {

    public static String generateCategoryCode(String prefix) {
        return prefix + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

}
