package com.hummingbird.kr.starbuckslike.auth.infrastucture;

import com.hummingbird.kr.starbuckslike.auth.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;

//for logout Request, this is blacklist of Refresh
public interface TokenRepository extends JpaRepository<Token, String> {


    public void logoutReqDo(String token);
}
