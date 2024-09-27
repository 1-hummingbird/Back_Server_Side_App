package com.hummingbird.kr.starbuckslike.media.infrastructrue;

import com.hummingbird.kr.starbuckslike.media.domain.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {
}
