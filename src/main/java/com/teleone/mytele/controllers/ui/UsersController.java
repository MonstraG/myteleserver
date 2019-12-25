package com.teleone.mytele.controllers.ui;

import com.teleone.mytele.db.role.Role;
import com.teleone.mytele.db.user.User;
import com.teleone.mytele.db.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users/*")
public class UsersController {

    @Autowired
    private UserService usersService;

    @GetMapping("/list")
    public String users(ModelMap model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userDetails", userDetails);
        List<User> users = usersService.getUsers();
        model.addAttribute("users", users);
        model.addAttribute("hasContent", users.size() > 0);
        return "/users/list";
    }

    @GetMapping("/add")
    public String add(ModelMap model) {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("user", new User());
        return "/users/add";
    }

    @PostMapping("/create")
    public String save(ModelMap model, @ModelAttribute User user) {
        user.setRole(Role.USER);
        usersService.save(user);
        return users(model);
    }

    @GetMapping("/delete/{id}")
    public String delete(ModelMap model, @PathVariable Long id) {
        usersService.remove(id);
        return users(model);
    }
}
