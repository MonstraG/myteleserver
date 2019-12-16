package com.teleone.mytele.controllers;

import com.teleone.mytele.db.message.MessageService;
import com.teleone.mytele.db.service.AdditionalServicesService;
import com.teleone.mytele.db.tariff.TariffService;
import com.teleone.mytele.db.ticket.TicketService;
import com.teleone.mytele.db.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/*")
public class HomeController {

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
    public String what() {
        return "tariffs.html";
    }

    @RequestMapping("/stats")
    public HashMap<String, Long> GetStats() {
        HashMap<String, Long> response = new HashMap<>();

        response.put("users", userService.getUsersCount());
        response.put("tickets", ticketService.getTicketsCount());
        response.put("additionalServices", additionalServicesService.getAdditionalServicesCount());
        response.put("messages", messagesService.getMessagesCount());
        response.put("tariffs", tariffsService.getTariffsCount());

        return response;
    }
}
