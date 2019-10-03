package com.teleone.mytele.controllers;

import com.teleone.mytele.db.user.User;
import com.teleone.mytele.db.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/users/*")
public class UserController {

    @Autowired private UserDao userDao;

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable String username) {
        if (userDao.userExists(username)) {
            return new ResponseEntity<>(userDao.getUserByUsername(username), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    //todo: ask - use ResponseEntity<String> when in effect using void?
    public ResponseEntity<String> createUser(@PathVariable String username) {
        if (userDao.userExists(username)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            User user = new User(username);
            userDao.createUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
