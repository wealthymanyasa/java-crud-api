package com.lyvepulse.restaurant.repository;

import com.lyvepulse.restaurant.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepo extends JpaRepository<Stock, Long> {

}