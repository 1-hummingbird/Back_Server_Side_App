package com.hummingbird.kr.starbuckslike.redis.dto.out;

import com.hummingbird.kr.starbuckslike.redis.vo.out.RecentSearchResponseVo;
import com.hummingbird.kr.starbuckslike.redis.vo.out.SearchWordDetailResponseVo;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecentSearchResponseDto {
    private String userId;
    private List<SearchWordDetailResponseDto> searchWordDetails;
    public RecentSearchResponseVo toVo(){
        List<SearchWordDetailResponseVo> details = searchWordDetails != null ?
                searchWordDetails.stream().map(SearchWordDetailResponseDto::toVo).toList() : new ArrayList<>();
        return RecentSearchResponseVo.builder()
                .userId(userId)
                .searchWordDetails(details)
                .build();
    }
}
