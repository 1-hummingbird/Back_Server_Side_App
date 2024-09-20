package com.hummingbird.kr.starbuckslike.auth.domain;

import com.hummingbird.kr.starbuckslike.member.domain.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@ToString
@Slf4j
@Getter
@NoArgsConstructor
public class AuthUserDetail implements UserDetails {

    private String uuid;
    private String password;
    private String loginId;
    private Boolean isDeleted;
    private String nickname;

    public AuthUserDetail(Member member) {
        this.uuid = member.getMemberUID();
        this.password = member.getPassword();
        this.loginId = member.getLoginID();
        this.isDeleted = member.getIsDeleted();
        this.nickname = member.getNickname();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.uuid;
    }

    public String getNickname() {
        return this.nickname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !this.isDeleted;
    }
}