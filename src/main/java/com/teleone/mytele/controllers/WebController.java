package com.teleone.mytele.controllers;

import com.teleone.mytele.db.message.MessageService;
import com.teleone.mytele.db.service.AdditionalServicesService;
import com.teleone.mytele.db.tariff.TariffService;
import com.teleone.mytele.db.ticket.TicketService;
import com.teleone.mytele.db.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/*")
public class WebController {

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TariffService tariffsService;

    @Autowired
    private MessageService messagesService;

    @Autowired
    private AdditionalServicesService additionalServicesService;

    @RequestMapping("/")
    public String home(ModelMap model) {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HashMap<String, Long> stats = getStats();
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("stats", stats);
        return "home";
    }

    @RequestMapping("/stats")
    public HashMap<String, Long> getStats() {
        HashMap<String, Long> response = new HashMap<>();

        response.put("users", userService.getUsersCount());
        response.put("tickets", ticketService.getTicketsCount());
        response.put("additionalServices", additionalServicesService.getAdditionalServicesCount());
        response.put("messages", messagesService.getMessagesCount());
        response.put("tariffs", tariffsService.getTariffsCount());

        return response;
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

}
