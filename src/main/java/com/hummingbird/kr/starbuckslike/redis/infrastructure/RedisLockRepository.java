package com.hummingbird.kr.starbuckslike.redis.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class RedisLockRepository {
    private final RedisTemplate<String, String> redisTemplate;

    /**
     * 리뷰
     */
    // 리뷰에 대한 락
    public Boolean reviewLock(Long reviewId) {
        return redisTemplate
                .opsForValue()
                .setIfAbsent(generateReviewKey(reviewId),
                        "lock", Duration.ofMillis(1_000)); // 1초 동안 락 유지
    }
    // 리뷰에 대한 락 해제
    public Boolean reviewUnlock(Long reviewId) {
        return redisTemplate.delete(generateReviewKey(reviewId));
    }

    // 리뷰 ID에 대한 고유 키 생성
    private String generateReviewKey(Long reviewId) {
        return "review:lock:" + reviewId;
    }


}