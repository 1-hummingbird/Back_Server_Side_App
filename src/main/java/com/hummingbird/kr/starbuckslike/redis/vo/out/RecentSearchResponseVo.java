package com.hummingbird.kr.starbuckslike.redis.vo.out;

import com.hummingbird.kr.starbuckslike.redis.dto.out.SearchWordDetailResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RecentSearchResponseVo {
    private String userId;
    private List<SearchWordDetailResponseVo> searchWordDetails;
}
