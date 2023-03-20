package com.lyvepulse.restaurant.controller;

import java.util.List;
import java.util.Optional;


import com.lyvepulse.restaurant.repository.StockRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lyvepulse.restaurant.entity.Stock;


@Slf4j
@RestController
public class StockController {

    @Autowired
    StockRepo stockRepo;

    @PostMapping("/stocks")
    public ResponseEntity<Stock> save(@RequestBody Stock stock) {
        try {
            return new ResponseEntity<>(stockRepo.save(stock), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/stocks")
    public ResponseEntity<List<Stock>> getAllOrders() {
        try {
            List<Stock> list = stockRepo.findAll();
            if (list.isEmpty() || list.size() == 0) {
                return new ResponseEntity<List<Stock>>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<List<Stock>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/stocks/{id}")
    public ResponseEntity<Stock> getSingleStock(@PathVariable Long id) {
        Optional<Stock> stock = stockRepo.findById(id);

        if (stock.isPresent()) {
            return new ResponseEntity<Stock>(stock.get(), HttpStatus.OK);
        }

        return new ResponseEntity<Stock>(HttpStatus.NOT_FOUND);
    }


//    @PutMapping("/stocks/{id}")
//    public ResponseEntity<Stock> updateStock@RequestBody Stock stock) {
//
//        try {
//            return new ResponseEntity<Stock>(stockRepo.save(stock), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @DeleteMapping("/stocks/{id}")
    public ResponseEntity<HttpStatus> deleteStock(@PathVariable Long id) {
        try {
            Optional<Stock> stock = stockRepo.findById(id);
            if (stock.isPresent()) {
                stockRepo.delete(stock.get());
            }
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}