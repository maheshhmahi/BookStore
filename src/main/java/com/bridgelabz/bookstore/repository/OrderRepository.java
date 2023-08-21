package com.bridgelabz.bookstore.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.bookstore.model.Order;


public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value="select * from order_entity where user_user_id = :userId",nativeQuery = true)
    List<Order> findByUserId(UUID userId);


}
