package com.teleone.mytele.db.tickets;

import com.teleone.mytele.db.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

    public boolean closeTicket(Long id) {
        Optional<Ticket> ticket = find(id);
        if (ticket.isPresent()) {
            ticket.get().setOpen(false);
            return true;
        }
        return false;
    }

    public boolean openTicket(Long id) {
        Optional<Ticket> ticket = find(id);
        if (ticket.isPresent()) {
            ticket.get().setOpen(true);
            return true;
        }
        return false;
    }

    public boolean create(Ticket ticket, User user) {
        if (user.getRole().name() == "USER") {
                ticketRepository.save(ticket);
                return true;
        }
        return false;
    }

    public boolean assignModerator(Long id, Long moderator, User user) {
        Optional<Ticket> ticket = find(id);
        if (ticket.isPresent() && (user.getRole().name().equals("MOD") || user.getRole().name().equals("ADMIN"))) {
            ticket.get().setModerator(moderator);
        }
        return false;
    }

    public Long getTicketsCount() {
        return ticketRepository.count();
    }

    public Set<Ticket> getTickets(User user) {
        String role = user.getRole().name();
        if (role.equals("USER")) {
            return ticketRepository.findByAuthor(user.getId());
        }
        if (role.equals("MOD")) {
            return ticketRepository.findByModerator(user.getId());
        }
        HashSet<Ticket> set = new HashSet<>();
        ticketRepository.findAll().forEach(set::add);
        return set;
    }

}
