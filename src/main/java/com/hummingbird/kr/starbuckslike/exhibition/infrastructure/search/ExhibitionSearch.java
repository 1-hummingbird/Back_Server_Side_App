package com.hummingbird.kr.starbuckslike.exhibition.infrastructure.search;


import com.hummingbird.kr.starbuckslike.exhibition.dto.out.ExhibitionDetailResponseDto;
import com.hummingbird.kr.starbuckslike.exhibition.dto.out.ExhibitionListResponseDto;

import java.util.List;

public interface ExhibitionSearch {

    /**
     * 모든 기획전 이름을 조회.
     * 현재 날짜가 기획전 시작~끝 날짜에 유효해야 함
     */
    List<ExhibitionListResponseDto> findAllExhibitionNames();

    // 기획전의 상세 내용 조회.
    ExhibitionDetailResponseDto findExhibitionDetail(Long id);

}
