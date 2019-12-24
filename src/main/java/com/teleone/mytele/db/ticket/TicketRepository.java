package com.teleone.mytele.db.ticket;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
    Ticket findById(long id);

    boolean existsById(long id);

    List<Ticket> findByModerator(long id);

    List<Ticket> findByAuthor(long id);

}
