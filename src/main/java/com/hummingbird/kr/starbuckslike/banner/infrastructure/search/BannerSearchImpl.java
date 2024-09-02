package com.hummingbird.kr.starbuckslike.banner.infrastructure.search;

import com.hummingbird.kr.starbuckslike.banner.domain.QBanner;
import com.hummingbird.kr.starbuckslike.banner.dto.BannerDto;
import com.hummingbird.kr.starbuckslike.banner.dto.QBannerDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.hummingbird.kr.starbuckslike.banner.domain.QBanner.banner;

@Repository
public class BannerSearchImpl implements  BannerSearch{
    private final JPAQueryFactory factory;
    public BannerSearchImpl(EntityManager em) {
        this.factory = new JPAQueryFactory(em);
    }

    @Override
    public List<BannerDto> findAllBannerDto() {
        return factory
                .select(new QBannerDto(banner.id, banner.image, banner.url))
                .from(banner)
                .orderBy(banner.seq.asc())
                .fetch();
    }

}
