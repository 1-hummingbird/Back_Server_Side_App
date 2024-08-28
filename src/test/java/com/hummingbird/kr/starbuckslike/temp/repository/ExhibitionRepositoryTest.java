package com.hummingbird.kr.starbuckslike.temp.repository;

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

    @Test
    void testFindAllExhibitionNames(){
        List<String> result = exhibitionRepository.findAllExhibitionNames();
        for (String eName : result) {
            log.info(eName);
        }

    }

    @Test
    void testFindExhibitionDetail(){
        Long id = 1L;
        String exhibitionDetail = exhibitionRepository.findExhibitionDetail(id);

        log.info(exhibitionDetail);
    }

}