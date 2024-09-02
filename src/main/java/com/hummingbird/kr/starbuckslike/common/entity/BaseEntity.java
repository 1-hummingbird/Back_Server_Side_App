package com.hummingbird.kr.starbuckslike.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public class BaseEntity {

    @Column(updatable = false)
    private LocalDateTime createdAt; // 최초 생성일

    private LocalDateTime updatedAt; // 마지막 수정일
    @PrePersist // 저장 전에 동작
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }
    @PreUpdate // 업데이트 전에 동작
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
