package com.hummingbird.kr.starbuckslike.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Date> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Date> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}