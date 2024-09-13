package com.hummingbird.kr.starbuckslike;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.hummingbird.kr.starbuckslike")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		// 개발1

	}

}
