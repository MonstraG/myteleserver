package com.teleone.mytele.controllers.ui;

import com.teleone.mytele.db.message.Message;
import com.teleone.mytele.db.message.MessageService;
import com.teleone.mytele.db.ticket.Ticket;
import com.teleone.mytele.db.ticket.TicketService;
import com.teleone.mytele.db.user.User;
import com.teleone.mytele.db.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tickets/*")
public class TicketsController {

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private MessageService messageService;


    @GetMapping("/list")
    public String tickets(ModelMap model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userDetails", userDetails);
        User user = userService.find(userDetails.getUsername());
        List<Ticket> tickets = ticketService.getTickets(user);
        model.addAttribute("tickets", tickets);
        model.addAttribute("hasContent", tickets.size() > 0);
        return "/tickets/list";
    }

    @GetMapping("/{id}")
    public String ticket(ModelMap model, @PathVariable Long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userDetails", userDetails);

        HashMap<Long, String> names = new HashMap<>();

        User user = userService.find(userDetails.getUsername());
        Optional<Ticket> ticket = ticketService.find(id);
        if (!ticket.isPresent()) {
            return "/error";
        }
        names.put(user.getId(), user.getUsername());

        Optional<User> moderator = userService.find(ticket.get().getModerator());
        moderator.ifPresent(moder -> {
            names.put(moder.getId(), moder.getUsername());
            model.addAttribute("moderator", moder);
        });

        Optional<User> author = userService.find(ticket.get().getAuthor());
        if (!author.isPresent()) {
            return "/error";
        }
        names.put(author.get().getId(), author.get().getUsername());

        model.addAttribute("hasMod", moderator.isPresent());
        model.addAttribute("ticket", ticket.get());
        model.addAttribute("user", user);
        model.addAttribute("names", names);

        return "/tickets/view";
    }

    @GetMapping("/add")
    public String add(ModelMap model) {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("message", new Message());
        return "/tickets/add";
    }

    @PostMapping("/create")
    public String save(ModelMap model, @ModelAttribute Ticket ticket, @ModelAttribute Message message) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.find(userDetails.getUsername());
        message.setAuthor(user.getId());
        ticket.setAuthor(user.getId());
        ticket.addMessage(message);
        ticket.setOpenStatus(true);
        ticketService.save(ticket);

        message.setTicket(ticket);
        messageService.save(message);
        return tickets(model);
    }

    @GetMapping("/close/{id}")
    public String close(ModelMap model, @PathVariable Long id) {
        ticketService.closeTicket(id);
        return ticket(model, id);
    }

    @GetMapping("/open/{id}")
    public String open(ModelMap model, @PathVariable Long id) {
        ticketService.openTicket(id);
        return ticket(model, id);
    }

    @GetMapping("/{id}/addMessage")
    public String addMessage(ModelMap model, @PathVariable Long id) {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("ticketId", id);
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("newMessage", new Message());
        return "/tickets/addMessage";
    }

    @PostMapping("/{ticketId}/addMessage")
    public String saveMessage(ModelMap model, @PathVariable Long ticketId, @ModelAttribute Message newMessage) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.find(userDetails.getUsername());
        newMessage.setAuthor(user.getId());
        Optional<Ticket> optionalTicket = ticketService.find(ticketId);
        if (!optionalTicket.isPresent()) {
            return "/error";
        }

        Ticket ticket = optionalTicket.get();

        ticket.addMessage(newMessage);
        ticketService.save(ticket);

        newMessage.setTicket(ticket);
        messageService.save(newMessage);
        return ticket(model, ticketId);
    }
}
