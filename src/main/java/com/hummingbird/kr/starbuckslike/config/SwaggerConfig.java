package com.hummingbird.kr.starbuckslike.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "STARBUCKS like Service API",
                version = "v1",
                description = "Spharos Academy 5th, team 6's STARBUCKS like Service API Docs"
        )
)

@SecurityScheme(
        name = "Bearer Auth",
        type = io.swagger.v3.oas.annotations.enums.SecuritySchemeType.HTTP,
        bearerFormat = "JWT", scheme = "bearer"
)

@Profile("!prod")
@Configuration
@ComponentScan(basePackages = "com.hummingbird.kr.starbuckslike")
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        String[] paths = { "/api/v1/**" };
        return GroupedOpenApi.builder()
                .group("public-api")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    @Primary
    public SwaggerUiConfigProperties swaggerUiConfigProperties() {
        SwaggerUiConfigProperties properties = new SwaggerUiConfigProperties();
        properties.setDisplayRequestDuration(true);
        return properties;
    }
}
