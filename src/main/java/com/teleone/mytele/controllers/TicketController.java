package com.teleone.mytele.controllers;

import com.teleone.mytele.db.message.Message;
import com.teleone.mytele.db.ticket.Ticket;
import com.teleone.mytele.db.ticket.TicketService;
import com.teleone.mytele.db.user.User;
import com.teleone.mytele.db.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
@RequestMapping("/api/tickets/*")
public class TicketController extends AbstractController {

    @Autowired
    TicketService ticketService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/{ticketId}", method = RequestMethod.GET)
    public ResponseEntity<Ticket> getTickets(@PathVariable Long ticketId) {
        return ticketService.find(ticketId)
                .map(ticket -> new ResponseEntity<>(ticket, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<String> createTicket(@PathVariable Long userId, @RequestBody Message initialMessage) {
        Optional<User> user = userService.find(userId);
        if (!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Ticket ticket = new Ticket();
        ticket.addMessage(initialMessage);
        ticket.setOpenStatus(true);
        ticket.setAuthor(userId);

        return booleanResponse(ticketService.create(ticket, user.get()));
    }

    @RequestMapping(value = "/{ticketId}/assign", method = RequestMethod.POST)
    public ResponseEntity<String> assignTicket(@PathVariable Long ticketId, @RequestBody Long moderatorId) {
        if (!ticketService.exists(ticketId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return userService.find(moderatorId)
                .map(moderator -> booleanResponse(ticketService.assignModerator(ticketId, moderator)))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/{ticketId}/close", method = RequestMethod.GET)
    public ResponseEntity<String> closeTicket(@PathVariable Long ticketId) {
        if (!ticketService.exists(ticketId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return booleanResponse(ticketService.closeTicket(ticketId));
    }

    @RequestMapping(value = "/{ticketId}/open", method = RequestMethod.GET)
    public ResponseEntity<String> openTicket(@PathVariable Long ticketId) {
        if (!ticketService.exists(ticketId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return booleanResponse(ticketService.openTicket(ticketId));
    }
}
