package com.lyvepulse.restaurant.controller;

import java.util.List;
import java.util.Optional;

import com.lyvepulse.restaurant.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lyvepulse.restaurant.entity.User;

@Slf4j
@RequestMapping("/user")
@RestController
class UsersController {

    @Autowired
    UserRepository userRepo;

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User users) {
        try {
            return new ResponseEntity<>(userRepo.save(users), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> list = userRepo.findAll();
            if (list.isEmpty() || list.size() == 0) {
                return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<List<User>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getSingleUser(@PathVariable Long id) {
        Optional<User> user = userRepo.findById(id);

        if (user.isPresent()) {
            return new ResponseEntity<User>(user.get(), HttpStatus.OK);
        }

        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/email/{email}") //FIXME you cant return a wild card from a response entity, the api reader wont know the response you want to give him/her
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {

        Optional<User> user = userRepo.findByEmail(email);
        //log.info("The Provided email {}",email);
        if (user.isPresent()) {
            return new ResponseEntity<User>(user.get(), HttpStatus.OK);
        }

        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);


    }
    @GetMapping("/role/{role}")
    public ResponseEntity<User> getUserByRole(@PathVariable String role) {

        //FIXME Remove all the logic from the controller layer

        Optional<User> user = userRepo.findByRole(role);

        if (user.isPresent()) {
            return new ResponseEntity<User>(user.get(), HttpStatus.OK);
        }

        //FIXME thers is no logic layer total

        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);


    }

    @PutMapping("userId/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User users) {

        try {

            return new ResponseEntity<>(userRepo.save(users), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//FIXME it means
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        try {
            Optional<User> user = userRepo.findById(id);
            if (user.isPresent()) {
                userRepo.delete(user.get());
            }
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}