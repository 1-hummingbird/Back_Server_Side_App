package com.hummingbird.kr.starbuckslike.auth.infrastructure;

import com.hummingbird.kr.starbuckslike.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Member, Long> {

  Optional<Member> findByMemberUID(String uuid);

  @Query("SELECT m FROM Member m WHERE m.loginID = :loginID")
  Optional<Member> findByLoginID(@Param("loginID") String loginID);

  @Modifying
  @Transactional
  @Query("UPDATE Member m SET m.password = :password WHERE m.memberUID = :uuid")
  void updatePasswordByUuid(@Param("uuid") String uuid, @Param("password") String password);
}