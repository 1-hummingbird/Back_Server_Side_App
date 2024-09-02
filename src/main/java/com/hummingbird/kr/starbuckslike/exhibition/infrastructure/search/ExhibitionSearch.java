package com.hummingbird.kr.starbuckslike.exhibition.infrastructure.search;


import com.hummingbird.kr.starbuckslike.exhibition.dto.ExhibitionDetailDto;
import com.hummingbird.kr.starbuckslike.exhibition.dto.ExhibitionListDto;

import java.util.List;

public interface ExhibitionSearch {

    /**
     * 모든 기획전 이름을 조회.
     * 현재 날짜가 기획전 시작~끝 날짜에 유효해야 함
     */
    List<ExhibitionListDto> findAllExhibitionNames();

    // 기획전의 상세 내용 조회.

    ExhibitionDetailDto findExhibitionDetail(Long id);

}
