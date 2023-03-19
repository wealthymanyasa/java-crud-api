package com.lyvepulse.restaurant.repository;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lyvepulse.restaurant.entity.Users;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {
Optional<Users> findByEmail(String email);
}