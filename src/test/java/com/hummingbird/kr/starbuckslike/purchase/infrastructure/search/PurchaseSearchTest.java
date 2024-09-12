package com.hummingbird.kr.starbuckslike.purchase.infrastructure.search;

import com.hummingbird.kr.starbuckslike.member.domain.Member;
import com.hummingbird.kr.starbuckslike.member.infrastructrue.MemberRepository;
import com.hummingbird.kr.starbuckslike.purchase.dto.out.PurchaseListResponseDto;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class PurchaseSearchTest {
    @Autowired
    PurchaseSearch purchaseSearch;
    @Autowired
    MemberRepository memberRepository;
    @Test
    void findPurchaseByUuidTest(){
         String uuid = memberRepository.findById(1L).orElseThrow().getMemberUID();

        List<PurchaseListResponseDto> res = purchaseSearch.findPurchaseByUuid(uuid);
        res.forEach(log::info);

    }

}