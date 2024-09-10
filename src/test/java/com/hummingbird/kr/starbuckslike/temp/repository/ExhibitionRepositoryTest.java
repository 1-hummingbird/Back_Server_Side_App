package com.hummingbird.kr.starbuckslike.temp.repository;

import com.hummingbird.kr.starbuckslike.exhibition.dto.out.ExhibitionDetailResponseDto;
import com.hummingbird.kr.starbuckslike.exhibition.dto.out.ExhibitionListResponseDto;
import com.hummingbird.kr.starbuckslike.exhibition.infrastructure.ExhibitionRepository;
import com.hummingbird.kr.starbuckslike.exhibition.infrastructure.search.ExhibitionSearch;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
class ExhibitionRepositoryTest {

    @Autowired
    private ExhibitionRepository exhibitionRepository;

    @Autowired
    private ExhibitionSearch exhibitionSearch;

    // 모든 기획전리스트 DTO 가져옴. 날짜 유효해야 함.
    @Test
    void testFindAllExhibitionNames(){
        List<ExhibitionListResponseDto> result = exhibitionSearch.findAllExhibitionNames();
        for (ExhibitionListResponseDto dto : result) {
            log.info(dto);
        }

    }

    @Test
    void testFindExhibitionDetail(){
        Long id = 1L;
        //String exhibitionDetail = exhibitionRepository.findExhibitionDetail(id);
        ExhibitionDetailResponseDto exhibitionDetail = exhibitionSearch.findExhibitionDetail(id);

        log.info(exhibitionDetail);
    }

}