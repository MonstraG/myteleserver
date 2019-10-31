package com.teleone.mytele.controllers;

import com.teleone.mytele.db.user.User;
import com.teleone.mytele.db.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/users/*")
public class UserController {

    @Autowired UserRepository repository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable int id) {
        if (repository.existsById(id)) {
            return new ResponseEntity<>(repository.findById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    //todo: ask - use ResponseEntity<String> when in effect using void?
    public ResponseEntity<String> createUser(@RequestBody String username, @RequestBody String password) {
        if (repository.existsByUsername(username)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            final User user = new User(username);
            user.setPassword(password);
            repository.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}