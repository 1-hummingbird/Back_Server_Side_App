package com.hummingbird.kr.starbuckslike.banner.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@ToString
public class BannerDto {
    private Long id; // 배너 Id

    private String image; // 베너 이미지 경로

    private String url; // 배너 클릭 시 이동할 url

    @QueryProjection
    public BannerDto(Long id, String image, String url) {
        this.id = id;
        this.image = image;
        this.url = url;
    }
}
