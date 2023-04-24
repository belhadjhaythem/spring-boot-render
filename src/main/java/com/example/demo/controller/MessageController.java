package com.example.demo.controller;

import com.example.demo.model.Message;
import com.example.demo.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/message")
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;

    @GetMapping(path="/all")
    ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        if (messages == null) {
            return new ResponseEntity<List<Message>>(HttpStatus.NOT_FOUND);
        }
        logger.info("Get messages with total : " + messages.size());
        return new ResponseEntity<List<Message>>(messages, HttpStatus.OK);
    }

    @GetMapping(path="/recipient/{id}")
    ResponseEntity<List<Message>> getRecipientMessages(@PathVariable int id) {
        List<Message> messages = messageService.getReceivedMessages(id);
        if (messages == null) {
            return new ResponseEntity<List<Message>>(HttpStatus.NOT_FOUND);
        }
        logger.info("Get recipient messages with total : " + messages.size());
        return new ResponseEntity<List<Message>>(messages, HttpStatus.OK);
    }

    @GetMapping(path="/sender/{id}")
    ResponseEntity<List<Message>> getSenderMessages(@PathVariable int id) {
        List<Message> messages = messageService.getSentMessages(id);
        if (messages == null) {
            return new ResponseEntity<List<Message>>(HttpStatus.NOT_FOUND);
        }
        logger.info("Get sender messages with total : " + messages.size());
        return new ResponseEntity<List<Message>>(messages, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    ResponseEntity<String> removeMessage(@PathVariable String id) {
        messageService.removeMessageById(id);
        logger.info("Deleted message : " + id);
        return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
    }

    @DeleteMapping("/clear/{id}")
    ResponseEntity<String> removeMessagesForArchitect(@PathVariable String id) {
        messageService.removeMessagesFromArchitect(Integer.parseInt(id));
        logger.info("Deleted message : " + id);
        return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
    }

    @PostMapping(path="/add")
    ResponseEntity<String> addMessage(@RequestBody Message a) {
        if (a == null) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        logger.info("Adding Message ..");
        messageService.addMessage(a);
        return new ResponseEntity<String>("Successfully added", HttpStatus.OK);
    }
}
