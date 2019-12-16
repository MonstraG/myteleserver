package com.teleone.mytele.controllers;

import com.teleone.mytele.db.ticket.Ticket;
import com.teleone.mytele.db.ticket.TicketService;
import com.teleone.mytele.db.user.User;
import com.teleone.mytele.db.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/users/*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

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
        if (userService.exists(id)) {
            Optional<User> user = userService.find(id);
            if (user.isPresent()) {
                return new ResponseEntity<>(user.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/create/cheat", method = RequestMethod.GET)
    public ResponseEntity<String> createUser() {
        if (userService.exists("admin")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            final User user = new User("admin");
            user.setPassword("admin");
            userService.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody String username, @RequestBody String password) {
        if (userService.exists(username)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            final User user = new User(username);
            user.setPassword(password);
            userService.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getUsers() {
        HashMap<String, Object> response = new HashMap<>();
        Set<User> users = userService.getUsers();
        response.put("users", users);
        response.put("count", users.size());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @RequestMapping(value = "/{userId}/all", method = RequestMethod.GET)
    public ResponseEntity<Set<Ticket>> getTickets(@PathVariable Long userId) {
        Optional<User> user = userService.find(userId);
        if (user.isPresent()) {
            Set<Ticket> result = ticketService.getTickets(user.get());
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}