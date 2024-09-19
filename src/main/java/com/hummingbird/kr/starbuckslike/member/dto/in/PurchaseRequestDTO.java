package com.hummingbird.kr.starbuckslike.member.dto.in;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class PurchaseRequestDTO {
    private String memberUID;
    private LocalDateTime endDate;
    private LocalDateTime startDate;

    public PurchaseRequestDTO(String memberUID, LocalDateTime endDate, LocalDateTime startDate) {
        this.memberUID = memberUID;
        this.endDate = endDate;
        this.startDate = startDate;
    }
}
