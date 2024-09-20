package com.hummingbird.kr.starbuckslike.redis.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchWordDetailResponseVo {
    private String searchWord;
}
