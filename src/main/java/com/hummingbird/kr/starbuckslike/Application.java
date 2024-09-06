package com.hummingbird.kr.starbuckslike;

import com.hummingbird.kr.starbuckslike.auth.infrastucture.TokenRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//auth.infrastructure.TokenRepository.Java를 JPA가 쓰지 않게 처리 했어요
@SpringBootApplication(scanBasePackages = "com.hummingbird.kr.starbuckslike")
@EnableJpaRepositories(basePackages = "com.hummingbird.kr.starbuckslike",
		excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
				classes = {TokenRepository.class}))
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
