package com.hummingbird.kr.starbuckslike.auth.application;

import com.hummingbird.kr.starbuckslike.auth.domain.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        CustomUserDetails memberOptional = (CustomUserDetails) customUserDetailsService.loadUserByUsername(username);
        if (memberOptional.isEnabled()) {
            CustomUserDetails member = memberOptional;
            if (passwordEncoder.matches(password, member.getPassword())) {
                // make jwt token with spring auth server's methods, token has memberUID and
                String token ="";
                return new UsernamePasswordAuthenticationToken(member.getMemberUID(), token, Collections.emptyList());
            }
        }
        throw new RuntimeException("Invalid username or password");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}