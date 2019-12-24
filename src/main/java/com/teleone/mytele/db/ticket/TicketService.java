package com.teleone.mytele.db.ticket;

import com.teleone.mytele.db.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public Optional<Ticket> find(Long id) {
        return ticketRepository.findById(id);
    }

    public boolean exists(Long id) {
        return ticketRepository.existsById(id);
    }

    public boolean save(Ticket ticket) {
        ticketRepository.save(ticket);
        return true;
    }

    public boolean closeTicket(Long id) {
        Optional<Ticket> ticket = find(id);
        if (ticket.isPresent()) {
            ticket.get().setOpenStatus(false);
            return save(ticket.get());
        }
        return false;
    }

    public boolean openTicket(Long id) {
        Optional<Ticket> ticket = find(id);
        if (ticket.isPresent()) {
            ticket.get().setOpenStatus(true);
            return save(ticket.get());
        }
        return false;
    }

    public boolean assignModerator(Long id, User moderator) {
        Optional<Ticket> ticket = find(id);
        if (ticket.isPresent() && (moderator.isEmployee())) {
            ticket.get().setModerator(moderator.getId());
            return save(ticket.get());
        }
        return false;
    }

    public Long getTicketsCount() {
        return ticketRepository.count();
    }

    public List<Ticket> getTickets(User user) {
        String role = user.getRole().name();
        if (role.equals("USER")) {
            return ticketRepository.findByAuthor(user.getId());
        }
        if (role.equals("MOD")) {
            return ticketRepository.findByModerator(user.getId());
        }

        //all tickets
        ArrayList<Ticket> set = new ArrayList<>();
        ticketRepository.findAll().forEach(set::add);
        return set;
    }

}
