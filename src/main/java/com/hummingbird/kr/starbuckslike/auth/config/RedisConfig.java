package com.hummingbird.kr.starbuckslike.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;

@Configuration
public class RedisConfig {

    @Value("${spring.garnet.host}")
    private String redisHost;

    @Value("${spring.garnet.port}")
    private int redisPort;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        LettuceConnectionFactory factory = new LettuceConnectionFactory(redisHost, redisPort);
        return factory;
    }

    @Bean
    public RedisTemplate<String, Date> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Date> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}