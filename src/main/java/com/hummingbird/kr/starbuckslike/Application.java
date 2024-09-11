package com.hummingbird.kr.starbuckslike;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages =
		{		"com.hummingbird.kr.starbuckslike",
				"com.hummingbird.kr.starbuckslike.auth.application",
				"com.hummingbird.kr.starbuckslike.auth.config",
				"com.hummingbird.kr.starbuckslike.auth.domain",
				"com.hummingbird.kr.starbuckslike.auth.dto",
				"com.hummingbird.kr.starbuckslike.auth.infrastructure",
				"com.hummingbird.kr.starbuckslike.auth.presentation",
				"com.hummingbird.kr.starbuckslike.auth.util",
				"com.hummingbird.kr.starbuckslike.auth.vo",
				"com.hummingbird.kr.starbuckslike.banner.domain",
				"com.hummingbird.kr.starbuckslike.banner.dto",
				"com.hummingbird.kr.starbuckslike.banner.infrastructure",
				"com.hummingbird.kr.starbuckslike.cart.application",
				"com.hummingbird.kr.starbuckslike.cart.domain",
				"com.hummingbird.kr.starbuckslike.cart.dto",
				"com.hummingbird.kr.starbuckslike.cart.infrastructure",
				"com.hummingbird.kr.starbuckslike.cart.presentation",
				"com.hummingbird.kr.starbuckslike.category.domain",
				"com.hummingbird.kr.starbuckslike.category.dto",
				"com.hummingbird.kr.starbuckslike.category.infrastructure",
				"com.hummingbird.kr.starbuckslike.common.entity",
				"com.hummingbird.kr.starbuckslike.config",
				"com.hummingbird.kr.starbuckslike.delivery.domain",
				"com.hummingbird.kr.starbuckslike.delivery.application",
				"com.hummingbird.kr.starbuckslike.delivery.dto",
				"com.hummingbird.kr.starbuckslike.delivery.infrastructure",
				"com.hummingbird.kr.starbuckslike.delivery.presentation",
				"com.hummingbird.kr.starbuckslike.delivery.vo",
				"com.hummingbird.kr.starbuckslike.exhibition.domain",
				"com.hummingbird.kr.starbuckslike.exhibition.dto",
				"com.hummingbird.kr.starbuckslike.exhibition.infrastructure",
				"com.hummingbird.kr.starbuckslike.member.domain",
				"com.hummingbird.kr.starbuckslike.member.dto",
				"com.hummingbird.kr.starbuckslike.product.application",
				"com.hummingbird.kr.starbuckslike.product.domain",
				"com.hummingbird.kr.starbuckslike.product.dto",
				"com.hummingbird.kr.starbuckslike.product.infrastructure",
				"com.hummingbird.kr.starbuckslike.product.presentation"
		})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

