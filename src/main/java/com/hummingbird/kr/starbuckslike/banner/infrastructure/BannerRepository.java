package com.hummingbird.kr.starbuckslike.banner.infrastructure;

import com.hummingbird.kr.starbuckslike.banner.domain.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<Banner,Long> {
}
