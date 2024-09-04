package com.hummingbird.kr.starbuckslike.auth.util;

import com.hummingbird.kr.starbuckslike.auth.application.AuthService;
import com.hummingbird.kr.starbuckslike.auth.domain.CustomUserDetails;
import com.hummingbird.kr.starbuckslike.auth.dto.LoginRequestDTO;
import com.hummingbird.kr.starbuckslike.auth.dto.LoginResponseDTO;
import com.hummingbird.kr.starbuckslike.member.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private final AuthService authService;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(AuthService authService, PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // Use authService to authenticate the user
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO(username, password);
        LoginResponseDTO loginResponseDTO = authService.login(loginRequestDTO);

        if (loginResponseDTO == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        // Fetch user details using memberUid to get authorities
        String memberUid = loginResponseDTO.getToken();
        Optional<Member> memberOptional = authService.findByMemberUID(memberUid);
        if (memberOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found.");
        }
        Member member = memberOptional.get();
        UserDetails userDetails = new CustomUserDetails(member);

        // Create and return an Authentication object with JWT token
        return new UsernamePasswordAuthenticationToken(userDetails,  userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}