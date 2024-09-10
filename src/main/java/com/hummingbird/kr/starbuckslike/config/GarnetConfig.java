package com.hummingbird.kr.starbuckslike.config;

// this is Configuration Class of Spring which control Garnet Connection
// Garnet is pork of Redis which created MS, developed in .NET 8.0, C#

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;

@Configuration
public class GarnetConfig {
    @Value("${garnet.host}")
    private String redisHost;

    @Value("${garnet.port}")
    private int redisPort;
    
    //Redis Connection Factory 객체 생성 : Redis - Spring App 연결 생성기입니다.
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        LettuceConnectionFactory factory = new LettuceConnectionFactory(redisHost, redisPort);
        return factory;
    }
    
    // RedisTemplate는 JPA Repository를 대신하는 Redis에 넘겨줄 객체를 정의하는 방식입니다.
    // RedisTemplate<key type, value type>의 구조를 활용하고요
    // Redis를 쓸 서비스에서 private final로 RedisTemplate을 가져온 다음
    // RedisTemplate.opsForValue().set(key, value, ttl, TimeUnit.SECONDS) 형식으로 값 정의
    // RedisTemplate.opsForValue().get(key) 를 통해 값 가져오기
    @Bean
    public RedisTemplate<String, Date> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Date> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}
