package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Message;
import com.example.demo.repository.MessageRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MessageService implements IMessageService{

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<Message> getAllMessages() {
        Iterable<Message> iterableMessages = messageRepository.findAll();
        return StreamSupport.stream(iterableMessages.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Message> getReceivedMessages(int id) {
        return messageRepository.findReceivedMessages(id);
    }

    @Override
    public List<Message> getSentMessages(int id) {
        return messageRepository.findSentMessages(id);
    }

    @Override
    public void removeMessage(Message m) {
        messageRepository.delete(m);
    }

    @Override
    public void removeMessageById(String id) {
        messageRepository.deleteById(id);
    }

    @Override
    public Message addMessage(Message m) {
        return messageRepository.save(m);
    }

    @Override
    public void removeMessages(List<Message> messages) {
        messageRepository.deleteAll(messages);
    }

    @Override
    public void removeMessagesFromArchitect(int id) {
        messageRepository.deleteMessagesForArchitect(id);
    }
}
