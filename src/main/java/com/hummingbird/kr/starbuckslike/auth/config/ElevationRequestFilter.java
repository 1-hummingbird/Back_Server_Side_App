package com.hummingbird.kr.starbuckslike.auth.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
// 마이페이지 들어가서 개인정보 수정할 때 2차 비밀번호 확인 관련한 커스텀 인증 필터 구현 중이에요
//this is custom filter for 2nd PW check for user, to protect Privacy data, by make harder to see or to edit
public class ElevationRequestFilter  extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authenticated = request.getHeader("2ndAuth");
        if (authenticated != null){
            try{
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime authenticatedTime = LocalDateTime.parse(authenticated);
                if (now.minusMinutes(5).isBefore(authenticatedTime)){
                    filterChain.doFilter(request, response);
                } else {
                    response.setStatus(403);
                    response.getWriter().write("Forbidden");
                }
            } catch (Exception e){
                response.setStatus(403);
                response.getWriter().write("Forbidden");
            }
        } else {
            response.setStatus(403);
            response.getWriter().write("Forbidden");
        }
    }
}
