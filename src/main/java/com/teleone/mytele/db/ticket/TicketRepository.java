package com.teleone.mytele.db.ticket;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
    Ticket findById(long id);

    boolean existsById(long id);

    Set<Ticket> findByModerator(long id);

    Set<Ticket> findByAuthor(long id);

}
