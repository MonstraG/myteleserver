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
    private String author;
    private String moderator;
    private Boolean status;

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

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getModerator() { return moderator; }

    public void setModerator(String moderator) { this.moderator = moderator; }

    public Boolean getStatus() {return status; }

    public void setStatus(Boolean status) { this.status = status; }
}
