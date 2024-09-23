package com.hummingbird.kr.starbuckslike.config;

import com.hummingbird.kr.starbuckslike.auth.util.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@ComponentScan(basePackages = "com.hummingbird.kr.starbuckslike")
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList(
                "http://localhost:3000","http://localhost:8080","https://team-hummingbird.shop","https://www.team-hummingbird.shop"));
	    config.setAllowedMethods(Arrays.asList("GET","POST"));
        config.addAllowedHeader("*");
        config.setExposedHeaders(List.of("Authorization"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorizeRequests -> authorizeRequests
                               /* .requestMatchers(
                                        "/api/v1/product/detail/**",
                                        "/api/v1/product/images/**",
                                        "/api/v1/product/info/**",
                                        "/api/v1/product/list/**",
                                        "/api/v1/product/options/**",
                                        "/api/v1/auth/**",
                                        "/api/v1/exhibition/**",
                                        "/api/v1/category/**",
                                        "/api/v1/product-category-list",
                                        "/api/v1/review/image/**",
                                        "/api/v1/review/info/**",
                                        "/api/v1/review/list/**",
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**",
                                        "/error",
                                        "/api/v1/batch/**"
                                )
                                .permitAll() */
                                .anyRequest()
				.premitAll()
                                //.authenticated()
                )
                .sessionManagement(
                        sessionManagement -> sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilter(corsFilter());
        return http.build();
    }
}
