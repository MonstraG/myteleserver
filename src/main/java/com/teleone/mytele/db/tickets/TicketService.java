package com.teleone.mytele.db.tickets;

import com.teleone.mytele.db.tarifs.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;
}
