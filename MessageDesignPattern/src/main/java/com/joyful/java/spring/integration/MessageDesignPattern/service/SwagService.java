package com.joyful.java.spring.integration.MessageDesignPattern.service;

import com.joyful.java.spring.integration.MessageDesignPattern.model.Swag;
import org.springframework.messaging.Message;

public interface SwagService {
    public void sendSwag(Message<Swag> message);
}
