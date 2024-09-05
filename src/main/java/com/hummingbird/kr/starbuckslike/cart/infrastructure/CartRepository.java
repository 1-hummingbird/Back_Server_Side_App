package com.hummingbird.kr.starbuckslike.cart.infrastructure;

import com.hummingbird.kr.starbuckslike.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
