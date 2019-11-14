package com.teleone.mytele.db.tickets;

import com.teleone.mytele.db.messages.Message;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long author;
    private Long moderator;
    private Boolean open;

    @OneToMany(mappedBy = "id")
    private Set<Message> messageSet;

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
