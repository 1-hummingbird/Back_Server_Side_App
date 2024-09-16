package com.hummingbird.kr.starbuckslike.auth.infrastructure;

import com.hummingbird.kr.starbuckslike.auth.domain.OauthInfo;
import com.hummingbird.kr.starbuckslike.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface OauthInfoRepository extends JpaRepository<OauthInfo, Long> {

    @Query("SELECT m FROM OauthInfo m WHERE m.oauthID = :oauthID AND m.oauthType = :oauthType")
    Optional<OauthInfo> findByOauthIDAndOauthType(@Param("oauthID") String oauthID, @Param("oauthType") String oauthType);

    @Query("SELECT m FROM OauthInfo m WHERE m.MemberUID = :MemberUID")
    List<OauthInfo> findBymemberUID(@Param("MemberUID")String memberUID);

}