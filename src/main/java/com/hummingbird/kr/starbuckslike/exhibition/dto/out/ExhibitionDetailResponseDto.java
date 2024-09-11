package com.hummingbird.kr.starbuckslike.exhibition.dto.out;

import com.hummingbird.kr.starbuckslike.exhibition.vo.ExhibitionDetailResponseVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 기획전 상세정보 DTO
 * @author 허정현
 */
@Data
@Builder
@NoArgsConstructor
public class ExhibitionDetailResponseDto {
    private String detail; // 긴 에티터 데이터 (html)

    public ExhibitionDetailResponseVo toVo(){
        return ExhibitionDetailResponseVo.builder().detail(detail).build();
    }

    @QueryProjection
    public ExhibitionDetailResponseDto(String detail) {
        this.detail = detail;
    }
}
