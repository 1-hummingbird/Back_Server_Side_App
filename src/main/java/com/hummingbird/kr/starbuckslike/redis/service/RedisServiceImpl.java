package com.hummingbird.kr.starbuckslike.redis.service;

import com.hummingbird.kr.starbuckslike.redis.dto.out.RecentSearchResponseDto;
import com.hummingbird.kr.starbuckslike.redis.dto.out.SearchWordDetailResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Log4j2
public class RedisServiceImpl implements  RedisService{

    private final RedisTemplate<String, String> redisTemplate;

    // 회원의 최대 저장가능한 최근검색어 개수
    private static final int MAX_RECENT_SEARCHES = 5;

    @Override
    public void addSearchWord(String userId, String searchWord) {
        String key = "search:" + userId;
        ListOperations<String, String> listOps = redisTemplate.opsForList();

        // 가장 앞에 검색어 추가
        listOps.leftPush(key, searchWord);

        // 5개를 초과하면 오래된 검색어부터 제거
        Long size = listOps.size(key);
        if (size != null && size > MAX_RECENT_SEARCHES) {
            listOps.rightPop(key);
        }
    }

    @Override
    public RecentSearchResponseDto getRecentSearchDto(String userId) {
        // 최근 검색어 조회
        List<SearchWordDetailResponseDto> searchWordDetails = getRecentSearchDetails(userId);
        return RecentSearchResponseDto.builder()
                .userId(userId)
                .searchWordDetails(searchWordDetails)
                .build();
    }

    @Override
    public void deleteSearchWord(String userId, String searchTerm) {
        String key = "search:" + userId;
        ListOperations<String, String> listOps = redisTemplate.opsForList();

        // 특정 검색어 삭제 (첫 번째로 일치하는 항목을 하나만 제거)
        listOps.remove(key, 1, searchTerm);
    }

    @Override
    public void deleteUserSearchKey(String userId) {
        String key = "search:" + userId;
        // 해당 key 삭제
        redisTemplate.delete(key);
    }

    // 최근 검색어 목록을 가져오는 메서드
    public List<SearchWordDetailResponseDto> getRecentSearchDetails(String userId) {
        String key = "search:" + userId;
        ListOperations<String, String> listOps = redisTemplate.opsForList();

        // List의 모든 항목을 가져옴
        List<String> searchWords = listOps.range(key, 0, -1);
        List<SearchWordDetailResponseDto> searchWordDetails = new ArrayList<>();

        if (searchWords != null) {
            for (String word : searchWords) {
                SearchWordDetailResponseDto detail = new SearchWordDetailResponseDto();
                detail.setSearchWord(word);
                searchWordDetails.add(detail);
            }
        }

        return searchWordDetails;
    }

    @Override
    public void recordToken(String token, Date expires){
        redisTemplate.opsForValue().set(token, "1",
            expires.getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean isTokenBlocked(String token){
        return redisTemplate.hasKey(token);
    }
}
