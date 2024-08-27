package com.hummingbird.kr.starbuckslike.temp.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * 기획전 엔티티
 * @author 허정현
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "exhibition")
public class Exhibition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exhibition_id")
    private Long id;

    @Column(name = "exhibition_name" , length = 100 , nullable = false)
    private String name;

    @Column(name = "full_description", columnDefinition = "LONGTEXT")
    private String fullDescription; // 기획전 설명 (html)

    @Column(name = "start_date")
    private String startDate; // 기획전 설명 (html)

    @Column(name = "end_date")
    private String endDate; // 기획전 설명 (html)


}
