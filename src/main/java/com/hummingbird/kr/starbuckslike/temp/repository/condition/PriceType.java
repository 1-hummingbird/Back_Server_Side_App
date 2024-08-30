package com.hummingbird.kr.starbuckslike.temp.repository.condition;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PriceType {
    BELOW_10000("1만원 이하", 0, 10000),
    BETWEEN_10000_AND_19999("1만원대", 10000, 19999),
    BETWEEN_20000_AND_29999("2만원대", 20000, 29999),
    BETWEEN_30000_AND_39999("3만원대", 30000, 39999),
    BETWEEN_40000_AND_49999("4만원대", 40000, 49999),
    ABOVE_50000("5만원 이상", 50000, Integer.MAX_VALUE);

    private final String label;
    private final int minPrice;
    private final int maxPrice;
}