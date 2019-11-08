package com.teleone.mytele.db.tickets;

import com.teleone.mytele.db.tarifs.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
