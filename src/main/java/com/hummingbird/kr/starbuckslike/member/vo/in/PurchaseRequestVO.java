package com.hummingbird.kr.starbuckslike.member.vo.in;

import java.time.LocalDateTime;

import com.hummingbird.kr.starbuckslike.member.dto.in.PurchaseRequestDTO;

import lombok.Getter;

@Getter
public class PurchaseRequestVO {
    private String memberUID;
    private LocalDateTime endDate;
    private LocalDateTime startDate;

    public PurchaseRequestDTO toDTO() {
        return new PurchaseRequestDTO(memberUID, endDate, startDate);
    }
}
