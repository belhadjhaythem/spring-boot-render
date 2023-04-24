package com.example.demo.service;

import com.example.demo.model.Message;

import java.util.List;

public interface IMessageService {

    public List<Message> getAllMessages();
    public List<Message> getReceivedMessages(int id);
    public List<Message> getSentMessages(int id);
    public void removeMessage(Message m);
    public void removeMessageById(String id);
    public Message addMessage(Message m);
    public void removeMessages(List<Message> gifts);
    public void removeMessagesFromArchitect(int id);
}
