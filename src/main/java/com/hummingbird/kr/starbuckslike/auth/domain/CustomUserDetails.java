package com.hummingbird.kr.starbuckslike.auth.domain;

import com.hummingbird.kr.starbuckslike.member.domain.Member;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

// 스프링 시큐리티 용 사용자 정보 Wrapper로 이해하시면 됩니다
// written by 김유석

public class CustomUserDetails implements UserDetails {

    private String uuid;
    private String password;
    private String loginid;
    private boolean isDeleted;

    public CustomUserDetails(Member member) {
        this.uuid = member.getMemberUID();
        this.password = member.getPassword();
        this.loginid = member.getLoginID();
        this.isDeleted = member.getIsDeleted();
        }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // Replace with member.getAuthorities() if applicable
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.loginid;
    }

    public String getMemberUID() {
        return this.uuid;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Implement logic if needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Implement logic if needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Implement logic if needed
    }

    @Override
    public boolean isEnabled() {
        return !this.isDeleted;
    }
}