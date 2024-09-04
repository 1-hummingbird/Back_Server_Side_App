package com.hummingbird.kr.starbuckslike.auth.application;

import com.hummingbird.kr.starbuckslike.auth.domain.CustomUserDetails;
import com.hummingbird.kr.starbuckslike.member.domain.Member;
import com.hummingbird.kr.starbuckslike.member.infrastructrue.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.function.Supplier;

@Service
//@DependsOn("authServiceImpl")
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

//    @Autowired
//    public CustomUserDetailService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    @Override
    public CustomUserDetails loadUserByUsername(String loginId) { Supplier<UsernameNotFoundException> s =
            () -> new UsernameNotFoundException("invalid username or password");

        Member m = memberRepository.findByid(loginId).orElseThrow(s);
        return new CustomUserDetails(m);
    }
}
