package com.lyvepulse.restaurant.controller;

import java.util.List;
import java.util.Optional;

import com.lyvepulse.restaurant.repository.OrderRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lyvepulse.restaurant.entity.Order;


@Slf4j
@RestController
public class OrderController {

    @Autowired
    OrderRepo orderRepo;

    @PostMapping("/orders")
    public ResponseEntity<Order> save(@RequestBody Order order) {
        try {
            return new ResponseEntity<>(orderRepo.save(order), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        try {
            List<Order> list = orderRepo.findAll();
            if (list.isEmpty() || list.size() == 0) {
                return new ResponseEntity<List<Order>>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<List<Order>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getSingleOrder(@PathVariable Long id) {
        Optional<Order> order = orderRepo.findById(id);

        if (order.isPresent()) {
            return new ResponseEntity<Order>(order.get(), HttpStatus.OK);
        }

        return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
    }
//    @GetMapping("/order/date-created/{created_at}")
//    public ResponseEntity<?> getOrderByDate(@PathVariable String created_at) {
//
//        Optional<Order> order = orderRepo.findOrderByDate(created_at);
//        if (order.isPresent()) {
//            return new ResponseEntity<Order>(order.get(), HttpStatus.OK);
//        }
//
//        return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
//
//
//    }


    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {

        try {
            return new ResponseEntity<Order>(orderRepo.save(order), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable Long id) {
        try {
            Optional<Order> order = orderRepo.findById(id);
            if (order.isPresent()) {
                orderRepo.delete(order.get());
            }
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}