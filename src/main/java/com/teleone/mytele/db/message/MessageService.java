package com.teleone.mytele.db.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Long getMessagesCount() {
        return messageRepository.count();
    }

}
