package com.hummingbird.kr.starbuckslike.member.infrastructrue;

import com.hummingbird.kr.starbuckslike.member.domain.Member;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberUID(String memberUid);
    Optional<Member> findByEmail(String email);
    Optional<Member> findByPhone(String phone);
    Optional<Member> findByLoginID(String loginID);
    Optional<Member> findByNickname(String nickname);
    Optional<List<Member>> findByBirth(Date birth);
    Optional<Member> findByEmailAndPhone(String email, String phone);
    Optional<Member> findByEmailAndLoginID(String email, String loginID);
    Optional<Member> findByPhoneAndLoginID(String phone, String loginID);
    Optional<Member> findByEmailAndPhoneAndLoginID(String email, String phone, String loginID);
    Optional<Member> findByEmailAndPhoneAndLoginIDAndNickname(String email, String phone, String loginID, String nickname);
    Optional<Member> findByEmailAndPhoneAndLoginIDAndNicknameAndBirth(String email, String phone, String loginID, String nickname, String birth);


}