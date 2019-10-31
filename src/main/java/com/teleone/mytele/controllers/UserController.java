package com.teleone.mytele.controllers;

import com.teleone.mytele.db.user.User;
import com.teleone.mytele.db.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/users/*")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        if (service.exists(id)) {
            Optional<User> user = service.find(id);
            if (user.isPresent()) {
                return new ResponseEntity<>(user.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody String username, @RequestBody String password) {
        if (service.exists(username)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            final User user = new User(username);
            user.setPassword(password);
            service.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}