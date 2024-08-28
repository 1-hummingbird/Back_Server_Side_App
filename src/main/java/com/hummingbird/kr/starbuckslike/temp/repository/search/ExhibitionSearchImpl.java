package com.hummingbird.kr.starbuckslike.temp.repository.search;

import com.hummingbird.kr.starbuckslike.temp.domain.QExhibition;
import com.hummingbird.kr.starbuckslike.temp.dto.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.hummingbird.kr.starbuckslike.temp.domain.QExhibition.exhibition;
import static com.hummingbird.kr.starbuckslike.temp.domain.QExhibitionProduct.exhibitionProduct;
import static com.hummingbird.kr.starbuckslike.temp.domain.QProduct.product;

/**
 * 기획전 조회 구현부
 * @author 허정현
 */
@Repository
public class ExhibitionSearchImpl implements  ExhibitionSearch{
    private final JPAQueryFactory queryFactory;
    public ExhibitionSearchImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    /*
        @Query("select e.name" +
                " from Exhibition e" +
                " where CURRENT_DATE between e.startDate and e.endDate" +
                " order by e asc"
        )
        List<String> findAllExhibitionNames(); => 아래의 findAllExhibitionNames() 로 변경
     */
    @Override
    public List<ExhibitionListDto> findAllExhibitionNames() {
        return queryFactory
                .select(new QExhibitionListDto(exhibition.id, exhibition.name)) // 전시회 이름 선택
                .from(exhibition)
                // 현재 날짜가 시작일과 종료일 사이에 있는지 확인
                .where(isCurrentDateBetween(exhibition.startDate, exhibition.endDate))
                .orderBy(exhibition.id.asc()) // 이름을 기준으로 오름차순 정렬
                .fetch();
    }

    /*
         // 기획전의 상세 내용 조회.
        @Query("select e.fullDescription" +
                " from Exhibition e" +
                " where e.id = :id" +
                " and e.isDeleted = false"
        )
        String findExhibitionDetail(@Param("id") Long id); => 아래의 findExhibitionDetail(Long id) 로 변경
     */
    @Override
    public ExhibitionDetailDto findExhibitionDetail(Long id) {
        //return null;
        return queryFactory
                .select(new QExhibitionDetailDto(exhibition.fullDescription))
                .from(exhibition)
                .where(
                        exhibition.id.eq(id)
                        .and(exhibition.isDeleted.isFalse()) // isDeleted가 false인지 확인
                )
                .fetchOne();
    }


    /**
     * 기획전의 모든 검색조건
     */

    // 현재 날짜가 주어진 시작일과 종료일 사이에 있는지 확인하는 조건
    private BooleanExpression isCurrentDateBetween(DatePath<LocalDate> startDate, DatePath<LocalDate> endDate) {
        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusDays(90);  // 30일 뒤의 날짜
        return startDate.loe(currentDate).and(endDate.goe(currentDate));
    }
}
