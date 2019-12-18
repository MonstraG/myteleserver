package com.teleone.mytele.controllers.ui;

import com.teleone.mytele.db.message.Message;
import com.teleone.mytele.db.ticket.Ticket;
import com.teleone.mytele.db.ticket.TicketService;
import com.teleone.mytele.db.user.User;
import com.teleone.mytele.db.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/tickets/*")
public class TicketsController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String tickets(ModelMap model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userDetails", userDetails);
        User user = userService.find(userDetails.getUsername());
        Set<Ticket> tickets = ticketService.getTickets(user);
        model.addAttribute("tickets", tickets);
        model.addAttribute("hasContent", tickets.size() > 0);
        return "/tickets/list";
    }

    @GetMapping("/view")
    public String ticket(ModelMap model, Long ticketId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userDetails", userDetails);

        User user = userService.find(userDetails.getUsername());
        Optional<Ticket> ticket = ticketService.find(ticketId);
        if (!ticket.isPresent()) {
            return "/error";
        }

        Optional<User> moderator = userService.find(ticket.get().getModerator());
        if (moderator.isPresent()) {
            model.addAttribute("mod", moderator);
        }

        model.addAttribute("hasMod", moderator.isPresent());

        model.addAttribute("ticket", ticket);
        model.addAttribute("user", user);
        return "/tickets/view";
    }

    @GetMapping("/add")
    public String add(ModelMap model) {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("message", new Message());
        return "/tickets/add";
    }

    @PostMapping("/create")
    public String save(ModelMap model, @ModelAttribute Message message) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.find(userDetails.getUsername());
        Ticket ticket = new Ticket();
        ticket.setAuthor(user.getId());
        ticket.addMessage(message);
        ticketService.create(ticket, user);
        return tickets(model);
    }
}
