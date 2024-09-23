package com.hummingbird.kr.starbuckslike.batch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishCountDto {
    private Long productId;
    private Long wishCount;
}