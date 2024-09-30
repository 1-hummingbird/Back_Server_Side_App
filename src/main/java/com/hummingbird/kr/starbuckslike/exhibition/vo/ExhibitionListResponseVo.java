package com.hummingbird.kr.starbuckslike.exhibition.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExhibitionListResponseVo {
    private Long id; // 기획전 id

    private String name; // 기획전 이름

    private String detail;
}
