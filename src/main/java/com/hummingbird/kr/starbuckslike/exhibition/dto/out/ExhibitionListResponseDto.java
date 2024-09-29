package com.hummingbird.kr.starbuckslike.exhibition.dto.out;


import com.hummingbird.kr.starbuckslike.exhibition.vo.ExhibitionListResponseVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 기획전 리스트 DTO
 * @author 허정현
 */
@Data
@Builder
@NoArgsConstructor
public class ExhibitionListResponseDto {
    private Long id; // 기획전 id

    private String name; // 기획전 이름
    private String detail;

    public ExhibitionListResponseVo toVo(){
        return ExhibitionListResponseVo.builder()
                .id(id)
                .name(name)
                .detail(detail)
                .build();
    }
    @QueryProjection
    public ExhibitionListResponseDto(Long id, String name, String detail) {
        this.id = id;
        this.name = name;
        this.detail = detail;
    }
}

