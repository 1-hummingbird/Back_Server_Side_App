package com.hummingbird.kr.starbuckslike.cart.infrastructure;

import com.hummingbird.kr.starbuckslike.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {


    // 장바구니 아이템 선택 삭제
    @Modifying(clearAutomatically = true) // 벌크쿼리 영속성 컨텍스트 초기화
    @Query("delete from Cart c" +
            " where c.id in :cartIds" +
            " and c.userUid = :userUid")
    void removeCartItems(@Param("cartIds") List<Long> cartIds, @Param("userUid") String userUid);


    // 회원의 전체 장바구니 삭제
    @Modifying(clearAutomatically = true)
    @Query("delete from Cart c" +
            " where c.userUid = :userUid")
    void removeAllCartItem(@Param("userUid") String userUid);
}
