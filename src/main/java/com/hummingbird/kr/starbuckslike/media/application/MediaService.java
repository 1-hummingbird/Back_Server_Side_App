package com.hummingbird.kr.starbuckslike.media.application;


import com.hummingbird.kr.starbuckslike.media.dto.in.MediaRequestDto;
import com.hummingbird.kr.starbuckslike.media.dto.out.MediaResponseDTO;

public interface MediaService {

    void addMedia(MediaRequestDto mediaRequestDto);
    void deleteMedia(Long mediaId);

    MediaResponseDTO getMedia(Long mediaId);
 //   List<MediaRequestDto> getMediaList();
}
