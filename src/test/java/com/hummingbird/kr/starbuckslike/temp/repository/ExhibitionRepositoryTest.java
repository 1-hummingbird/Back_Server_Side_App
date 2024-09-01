package com.hummingbird.kr.starbuckslike.temp.repository;

import com.hummingbird.kr.starbuckslike.temp.dto.ExhibitionDetailDto;
import com.hummingbird.kr.starbuckslike.temp.dto.ExhibitionListDto;
import com.hummingbird.kr.starbuckslike.temp.repository.search.ExhibitionSearch;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
        List<ExhibitionListDto> result = exhibitionSearch.findAllExhibitionNames();
        for (ExhibitionListDto dto : result) {
            log.info(dto);
        }

    }

    @Test
    void testFindExhibitionDetail(){
        Long id = 1L;
        //String exhibitionDetail = exhibitionRepository.findExhibitionDetail(id);
        ExhibitionDetailDto exhibitionDetail = exhibitionSearch.findExhibitionDetail(id);

        log.info(exhibitionDetail);
    }

}