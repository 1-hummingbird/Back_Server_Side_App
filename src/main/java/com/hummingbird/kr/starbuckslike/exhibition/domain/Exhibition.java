package com.hummingbird.kr.starbuckslike.exhibition.domain;

import com.hummingbird.kr.starbuckslike.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

/**
 * 기획전 엔티티
 * @author 허정현
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "exhibition")
public class Exhibition extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exhibition_id")
    private Long id;

    @Column(name = "exhibition_name" , length = 100 , nullable = false)
    private String name;

    @Column(name = "exhibition_title", nullable = false, length = 100)
    private String title; // 기획전 한줄 타이틀

    @Column(name = "full_description", columnDefinition = "LONGTEXT")
    private String fullDescription; // 기획전 설명 (html)

    @Column(name = "start_date")
    private LocalDate startDate; // 기획전 시작 날짜

    @Column(name = "end_date")
    private LocalDate endDate; // 기획전 종료 날짜

    @Column(name = "is_deleted", nullable = false)
    @ColumnDefault("false")
    private Boolean isDeleted = false ; // 기획전 삭제 여부

    // 기획전 이미지를 메인 베너로 쓰려고 했지만 베너 도메인 생성하자고 계획 수정되어 주석처리
//    @Column(name = "is_Banner" , nullable = false)
//    private Boolean isBanner = false; // banner 이미지 존재 여부


}
