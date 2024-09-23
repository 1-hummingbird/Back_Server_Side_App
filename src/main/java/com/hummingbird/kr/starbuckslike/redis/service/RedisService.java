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

    // 인증 요청을 기록(이메일/전화번호를 키, 시도 대상 코드를 값으로)
    void recordAuthChallenge(String media, String code, Date expires);

    // 성공한 인증 요청이면 위 인증 요청 기록을 덮어 씌움
    void recordAuthChallengeSuccess(String media, Date expires);

    // 인증 요청 정보를 불러옴
    String getAuthChallenge(String media);
}
