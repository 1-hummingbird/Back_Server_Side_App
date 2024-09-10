package com.hummingbird.kr.starbuckslike.exhibition.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExhibitionDetailResponseVo {
    private String detail; // 긴 에티터 데이터 (html)
}
