package com.hummingbird.kr.starbuckslike.exhibition.dto;


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
public class ExhibitionListDto {
    private Long id; // 기획전 id

    private String name; // 기획전 이름

    @QueryProjection
    public ExhibitionListDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}

