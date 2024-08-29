package com.hummingbird.kr.starbuckslike.member.infrastructrue;

import com.hummingbird.kr.starbuckslike.member.domain.Member;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberUID(String uuid);
    Optional<Member> findByid(String loginId);

    @Modifying
    @Transactional
    @Query("UPDATE Member m SET m.password = :password WHERE m.memberUID = :uuid")
    void updatePasswordByUuid(@Param("uuid") String uuid, @Param("password") String password);
}