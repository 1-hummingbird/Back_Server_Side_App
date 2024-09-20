package com.hummingbird.kr.starbuckslike.redis.dto.out;


import com.hummingbird.kr.starbuckslike.redis.vo.out.SearchWordDetailResponseVo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
public class SearchWordDetailResponseDto {
    private String searchWord;

    public SearchWordDetailResponseVo toVo(){
        return SearchWordDetailResponseVo.builder().searchWord(searchWord).build();
    }
}
