package com.hummingbird.kr.starbuckslike.temp.repository;

import com.hummingbird.kr.starbuckslike.temp.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
/**
 * 카테고리 조회  (querydsl 사용 X, @Qeury, 쿼리메소드)
 * 지금은 엔티티로 받는데 나중에 DTO나 VO로 받는 부분 추가해야 함
 * @author 허정현
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // 전체 카테고리
    @Query(value = "WITH RECURSIVE CategoryTree AS (" +
                        " SELECT category_id, category_name, parent, depth , path "   +
                        " FROM category " +
                        " WHERE parent IS NULL " +
                        " UNION ALL " +
                        " SELECT c.category_id, c.category_name, c.parent, c.depth , c.path " +
                        " FROM category c " +
                        " INNER JOIN CategoryTree ct ON ct.category_id = c.parent" +
                     ") " +
                    "SELECT * FROM CategoryTree ORDER BY depth, category_id",
            nativeQuery = true)
    List<Category> findAllCategory();

    //  id로 특정 최상위 카테고리부터 자식 카테고리 출력
    @Query(value = "WITH RECURSIVE CategoryTree AS (" +
                        " SELECT category_id, category_name, parent, depth , path "   +
                        " FROM category " +
                        " WHERE parent IS NULL " +
                        " AND category_id = :id"+
                        " UNION ALL " +
                        " SELECT c.category_id, c.category_name, c.parent, c.depth , c.path " +
                        " FROM category c " +
                        " INNER JOIN CategoryTree ct ON ct.category_id = c.parent" +
                        ") " +
            "SELECT * FROM CategoryTree ORDER BY depth, category_id",
            nativeQuery = true)
    List<Category> findAllCategoryById(@Param("id") Long id);




}