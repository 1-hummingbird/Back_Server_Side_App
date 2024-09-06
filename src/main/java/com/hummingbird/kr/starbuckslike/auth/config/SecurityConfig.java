package com.hummingbird.kr.starbuckslike.auth.config;

import com.hummingbird.kr.starbuckslike.auth.application.AuthService;
import com.hummingbird.kr.starbuckslike.auth.application.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(13);
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.setAllowedMethods(List.of("GET","POST"));
        config.setExposedHeaders(List.of("Authorization"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()))
                .csrf(csrf -> csrf.disable())
                // 어짜피 CORS나 CSRF나 Next.JS Front App - Spring Boot Back App의 소통이라 필요 없는 설정이에요.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
 //스프링 시큐리티가 세션 관리할필요 없어요, 상태 같은 거 없어요 하는 설정이에요. 왜? 우리 JWT(Token) 쓸 건데 상태 필요하나요?
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        // 인증 구역에는 일단 로그인 필요 없어요를 선언해 줄 거에요
                        // 인증 구역 특정 메소드들에 대한 보호는 별개의 필터로 걸죠
                        // 장바구닌 전체 아이템 삭제 등은 결국 메소드에 대해서 스프링 시큐리티가 멤버 이름을 뽑아서 걸러줘야 할걸요?
                        // 그럼으로 겸사 겸사 해야 해요.
                        .anyRequest().authenticated()
                        // 그리고 나머지는 인증 해야 해요 선언이에요
                )
                .authenticationProvider(customAuthenticationProvider());

        return http.build();
    }
}