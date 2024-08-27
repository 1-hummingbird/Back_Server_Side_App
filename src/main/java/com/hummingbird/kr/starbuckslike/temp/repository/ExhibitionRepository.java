package com.hummingbird.kr.starbuckslike.temp.repository;

import com.hummingbird.kr.starbuckslike.temp.domain.Exhibition;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 기획전
 * @author 허정현
 */
public interface ExhibitionRepository extends JpaRepository<Exhibition,Long> {


}
