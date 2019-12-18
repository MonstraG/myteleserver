package com.teleone.mytele.controllers;

import com.teleone.mytele.db.message.Message;
import com.teleone.mytele.db.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/api/messages/*")
public class MessageController extends AbstractController {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<String> createMessage(@RequestBody Message message) {
        return booleanResponse(messageService.create(message));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<String> updateMessage(@RequestBody Message message) {
        return booleanResponse(messageService.update(message));
    }
}
