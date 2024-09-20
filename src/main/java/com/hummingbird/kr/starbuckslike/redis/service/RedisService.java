package com.hummingbird.kr.starbuckslike.redis.service;


import com.hummingbird.kr.starbuckslike.redis.dto.out.RecentSearchResponseDto;

public interface RedisService {

    /**
     * 최근검색어
     */
    // 최근 검색어 등록
    void addSearchWord(String userId, String searchWord);

    // 회원의 최근 검색어 조회
    RecentSearchResponseDto getRecentSearchDto(String userId);

    // 회원의 개별 최근 검색어 삭제
    void deleteSearchWord(String userId, String searchTerm);

    // 회원의 전체 최근 검색어 삭제
    void deleteUserSearchKey(String userId);


}
