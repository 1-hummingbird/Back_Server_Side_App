package com.hummingbird.kr.starbuckslike.auth.application;

import com.hummingbird.kr.starbuckslike.auth.domain.AuthUserDetail;
import com.hummingbird.kr.starbuckslike.auth.infrastructure.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserDetailService implements UserDetailsService {

    private final AuthRepository authRepository;
    @Override
    public UserDetails loadUserByUsername(String uuid)
            throws UsernameNotFoundException {
        return new AuthUserDetail(
                authRepository.findByMemberUID(uuid).orElseThrow(
                        ()-> new UsernameNotFoundException(uuid)));
    }

}