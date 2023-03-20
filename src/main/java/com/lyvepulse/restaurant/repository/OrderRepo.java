package com.lyvepulse.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lyvepulse.restaurant.entity.Order;

//import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
//    Optional<Order> findOrderByDate(String created_at);
}