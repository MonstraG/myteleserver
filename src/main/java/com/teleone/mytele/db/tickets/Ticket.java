package com.teleone.mytele.db.tickets;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long author;
    private Long moderator;
    private Boolean open;

    Ticket() { }

    public Ticket(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthor() { return author; }

    public void setAuthor(Long author) { this.author = author; }

    public Long getModerator() { return moderator; }

    public void setModerator(Long moderator) { this.moderator = moderator; }

    public Boolean getOpen() {return open; }

    public void setOpen(Boolean open) { this.open = open; }
}
