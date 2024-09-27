package com.hummingbird.kr.starbuckslike.media.dto.in;

import com.hummingbird.kr.starbuckslike.media.domain.Media;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MediaRequestDto {

    private String mediaUrl;
    private String mediaName;
    private String mediaType;

    @Builder
    public MediaRequestDto(String mediaUrl, String mediaName, String mediaType){
        this.mediaName = mediaName;
        this.mediaType = mediaType;
        this.mediaUrl = mediaUrl;
    }

    public Media toEntity() {
        return Media.builder()
                .mediaUrl(mediaUrl)
                .mediaType(mediaType)
                .mediaName(mediaName)
                .build();
    }
}

