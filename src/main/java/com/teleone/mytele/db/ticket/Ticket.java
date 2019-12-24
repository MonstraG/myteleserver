package com.teleone.mytele.db.ticket;

import com.teleone.mytele.db.message.Message;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long author;
    private Long moderator;
    private String topic;
    private Boolean open;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "messageId" )
    private List<Message> messageSet;

    public Ticket() {
    }

    public Ticket(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public Long getModerator() {
        return moderator;
    }

    public void setModerator(Long moderator) {
        this.moderator = moderator;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpenStatus(Boolean open) {
        this.open = open;
    }

    public void addMessage(Message message) {
        if (this.messageSet == null) {
            this.messageSet = new ArrayList<>();
        }
        this.messageSet.add(message);
    }

    public List<Message> getMessageSet() {
        return messageSet;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
