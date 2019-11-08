package com.teleone.mytele.db.tickets;

import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
    Ticket findById(long id);

    Ticket findByAuthor(String author);

    boolean existsById(long id);

    boolean existsByAuthor(String author);

    boolean existsByIdOrAuthor(long id, String author);
}
