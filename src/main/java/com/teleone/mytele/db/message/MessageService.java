package com.teleone.mytele.db.message;

import com.teleone.mytele.db.user.User;
import com.teleone.mytele.db.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserService userService;

    public Long getMessagesCount() {
        return messageRepository.count();
    }

    private boolean save(Message message) {
        messageRepository.save(message);
        return true;
    }

    public boolean create(Message message) {
        if (message.getText().trim().equals("")) {
            return false;
        }

        Optional<User> user = userService.find(message.getAuthor());
        if (!user.isPresent() || user.get().isEmployee()) {
            return false;
        }

        return save(message);
    }

    public boolean update(Message message) {
        if (message.getText().trim().equals("")) {
            return false;
        }

        Optional<Message> oldMessage = messageRepository.findById(message.getId());
        if (!oldMessage.isPresent() || !oldMessage.get().getAuthor().equals(message.getAuthor())) {
            return false;
        }

        return save(message);
    }

}
