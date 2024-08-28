package com.hummingbird.kr.starbuckslike.temp.repository;

import com.hummingbird.kr.starbuckslike.temp.domain.Exhibition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * 기획전
 * @author 허정현
 */
public interface ExhibitionRepository extends JpaRepository<Exhibition,Long> {

    // 모든 기획전 이름들을 조회. 기획전 시작~끝 날짜가 유효해야 함
    @Query("select e.name" +
            " from Exhibition e" +
            " where CURRENT_DATE between e.startDate and e.endDate" +
            " order by e asc"
    )
    List<String> findAllExhibitionNames();

    // 기획전의 상세 내용 조회.
    @Query("select e.fullDescription" +
            " from Exhibition e" +
            " where e.id = :id" +
            " and e.isDeleted = false"
    )
    String findExhibitionDetail(@Param("id") Long id);


}
