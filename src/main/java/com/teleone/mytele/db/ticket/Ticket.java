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

    @OneToMany(mappedBy = "ticket")
    private List<Message> MessageList;

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
        if (this.MessageList == null) {
            this.MessageList = new ArrayList<>();
        }
        this.MessageList.add(message);
    }

    public List<Message> getMessageList() {
        return MessageList;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
