package com.teleone.mytele.db.tickets;

import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
    Ticket findById(long id);

    boolean existsById(long id);
}
