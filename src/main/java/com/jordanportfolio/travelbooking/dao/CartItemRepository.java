package com.jordanportfolio.travelbooking.dao;

import com.jordanportfolio.travelbooking.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "cartItems", path = "cart-items")
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
