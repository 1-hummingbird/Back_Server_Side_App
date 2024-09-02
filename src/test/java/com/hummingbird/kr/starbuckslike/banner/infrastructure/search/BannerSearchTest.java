package com.hummingbird.kr.starbuckslike.banner.infrastructure.search;

import com.hummingbird.kr.starbuckslike.banner.dto.BannerDto;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class BannerSearchTest {

    @Autowired
    BannerSearch bannerSearch;

    @Test
    void testFindAllBannerDto(){
        List<BannerDto> result = bannerSearch.findAllBannerDto();
        result.forEach(log::info);
    }

}