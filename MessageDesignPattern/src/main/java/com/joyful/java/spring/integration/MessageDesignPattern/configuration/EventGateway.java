package com.joyful.java.spring.integration.MessageDesignPattern.configuration;

import com.joyful.java.spring.integration.MessageDesignPattern.model.EventMessage;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway(name = "eventGateway", defaultRequestChannel = "eventChannel")
public interface EventGateway {

    @Gateway
    void publishEvent(Message<EventMessage> message);
}
