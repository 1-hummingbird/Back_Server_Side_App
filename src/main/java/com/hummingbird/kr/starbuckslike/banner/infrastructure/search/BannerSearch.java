package com.hummingbird.kr.starbuckslike.banner.infrastructure.search;

import com.hummingbird.kr.starbuckslike.banner.dto.BannerDto;

import java.util.List;

public interface BannerSearch {
    // 배너 seq 순서대로 조회
    List<BannerDto> findAllBannerDto();

}
