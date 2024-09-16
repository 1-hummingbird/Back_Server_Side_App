package com.hummingbird.kr.starbuckslike.auth.infrastructure;

import com.hummingbird.kr.starbuckslike.auth.domain.OauthInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthInfoRepository extends JpaRepository<OauthInfo, Long> {
    OauthInfo findByOauthIDAndOauthType(String OauthID, String OauthType);
    OauthInfo findBymemberUID(String memberUID);

}