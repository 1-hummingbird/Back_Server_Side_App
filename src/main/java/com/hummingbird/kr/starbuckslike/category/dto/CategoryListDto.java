package com.hummingbird.kr.starbuckslike.category.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class CategoryListDto {
    private Long id; // 카테고리 id

    private String name; // 카테고리 이름

    private String path; // 카테고리 id들의 경로

    @QueryProjection
    public CategoryListDto(Long id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }
}
