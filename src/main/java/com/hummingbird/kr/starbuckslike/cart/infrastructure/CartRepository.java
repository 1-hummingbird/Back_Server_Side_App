package com.hummingbird.kr.starbuckslike.cart.infrastructure;

import com.hummingbird.kr.starbuckslike.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {

}
