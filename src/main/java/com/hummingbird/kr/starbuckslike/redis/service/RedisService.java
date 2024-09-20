package com.hummingbird.kr.starbuckslike.redis.service;


import com.hummingbird.kr.starbuckslike.redis.dto.out.RecentSearchResponseDto;

import java.util.Date;

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
    
    // 로그아웃 관련 토큰 블랙리스트에 기록
    void recordToken(String token, Date expires);

    // 토큰이 블랙리스트에 있는지 확인
    boolean isTokenBlocked(String token);
}
