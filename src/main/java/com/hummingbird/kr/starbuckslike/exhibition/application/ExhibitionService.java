package com.hummingbird.kr.starbuckslike.exhibition.application;

import com.hummingbird.kr.starbuckslike.exhibition.dto.out.ExhibitionDetailResponseDto;
import com.hummingbird.kr.starbuckslike.exhibition.dto.out.ExhibitionListResponseDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface ExhibitionService {
    /**
     * SELECT
     */
    // 기획전 start~end 유효하면 가져옴
    List<ExhibitionListResponseDto> findAllExhibitionNames();

    // 기획전의 상세 내용 조회.
    ExhibitionDetailResponseDto findExhibitionDetail(Long id);


    /**
     * INSERT, UPDATE
     */
}
