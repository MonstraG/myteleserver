package com.teleone.mytele.db.tickets;

import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
    Ticket findById(long id);

    boolean existsById(long id);

    //todo:
    // 1. total amount of them
    // 2. all by moderator id
    // 3. all by user id
    // 4. close ticket
    // 5. open ticket

    //todo:
    // messages service
    // with:
    //  all messages by ticket
}
