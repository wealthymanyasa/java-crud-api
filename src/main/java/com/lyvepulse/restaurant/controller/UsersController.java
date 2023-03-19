package com.lyvepulse.restaurant.controller;

import java.util.List;
import java.util.Optional;

import com.lyvepulse.restaurant.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lyvepulse.restaurant.entity.Users;
import com.lyvepulse.restaurant.repository.UserRepo;

@Slf4j
@RestController
class UserController {

    @Autowired
    UserRepo userRepo;

    @PostMapping("/users")
    public ResponseEntity<Users> save(@RequestBody Users users) {
        try {
            return new ResponseEntity<>(userRepo.save(users), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUsers() {
        try {
            List<Users> list = userRepo.findAll();
            if (list.isEmpty() || list.size() == 0) {
                return new ResponseEntity<List<Users>>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<List<Users>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Users> getSingleUser(@PathVariable Long id) {
        Optional<Users> user = userRepo.findById(id);

        if (user.isPresent()) {
            return new ResponseEntity<Users>(user.get(), HttpStatus.OK);
        }

        return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/users/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {

        Optional<Users> user = userRepo.findByEmail(email);
        log.info("The Provided email {}",email);
        if (user.isPresent()) {
            return new ResponseEntity<Users>(user.get(), HttpStatus.OK);
        }

        return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);


    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Users> updateUser(@RequestBody Users users) {

        try {
            return new ResponseEntity<Users>(userRepo.save(users), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        try {
            Optional<Users> user = userRepo.findById(id);
            if (user.isPresent()) {
                userRepo.delete(user.get());
            }
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}