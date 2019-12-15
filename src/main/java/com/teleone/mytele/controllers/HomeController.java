package com.teleone.mytele.controllers;

import com.teleone.mytele.db.message.MessageService;
import com.teleone.mytele.db.service.AdditionalServicesService;
import com.teleone.mytele.db.tariff.TariffsService;
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
    UserService userService;

    @Autowired
    TicketService ticketService;

    @Autowired
    TariffsService tariffsService;

    @Autowired
    MessageService messagesService;

    @Autowired
    AdditionalServicesService additionalServicesService;

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
