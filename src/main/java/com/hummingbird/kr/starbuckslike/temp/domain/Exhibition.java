package com.hummingbird.kr.starbuckslike.temp.domain;

import com.hummingbird.kr.starbuckslike.temp.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Column(name = "full_description", columnDefinition = "LONGTEXT")
    private String fullDescription; // 기획전 설명 (html)

    @Column(name = "start_date")
    private LocalDate startDate; // 기획전 시작 날짜

    @Column(name = "end_date")
    private LocalDate endDate; // 기획전 종료 날짜

    @Column(name = "is_Banner" , nullable = false)
    private Boolean isBanner = false; // banner 이미지 존재 여부


}
