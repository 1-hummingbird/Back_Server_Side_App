package com.hummingbird.kr.starbuckslike.banner.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * 배너 엔티티
 * @author 허정현
 */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "banner")
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banner_id")
    private Long id;

    @Column(name = "banner_image" , nullable = false)
    private String image;

    @Setter
    @Column(name = "seq" , nullable = false)
    private Integer seq;

    @Column(name = "banner_url" , nullable = true)
    private String url;

    @Builder
    public Banner(String image, Integer seq, String url) {
        this.image = image;
        this.seq = seq;
        this.url = url;
    }
}
