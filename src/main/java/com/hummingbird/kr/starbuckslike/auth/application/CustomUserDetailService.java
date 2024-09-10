package com.hummingbird.kr.starbuckslike.auth.application;

import com.hummingbird.kr.starbuckslike.auth.domain.CustomUserDetails;
import com.hummingbird.kr.starbuckslike.auth.infrastructure.AuthRepository;
import com.hummingbird.kr.starbuckslike.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final AuthRepository authRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String loginId) { Supplier<UsernameNotFoundException> s =
            () -> new UsernameNotFoundException("invalid username or password");
        Member m = authRepository.findByid(loginId);
        if (m == null) {throw new RuntimeException();}
        return new CustomUserDetails(m);
    }
}