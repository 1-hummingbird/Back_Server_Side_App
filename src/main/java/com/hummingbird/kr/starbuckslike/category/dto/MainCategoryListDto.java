package com.hummingbird.kr.starbuckslike.category.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class MainCategoryListDto {
    private Long id; // 카테고리 id

    private String name; // 카테고리 이름

    private String path; // 카테고리 경로

    private String image; // 카테고리 이미지 경로

    @QueryProjection
    public MainCategoryListDto(Long id, String name, String path, String image) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.image = image;
    }
}
