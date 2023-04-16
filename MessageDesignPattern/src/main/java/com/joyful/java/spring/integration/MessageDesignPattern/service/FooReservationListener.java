package com.joyful.java.spring.integration.MessageDesignPattern.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class FooReservationListener {
    private static final Logger logger =
            LogManager.getLogger(FooReservationListener.class);

    @ServiceActivator(inputChannel = "fooReservationListenerChannel")
    public void handleMessage(Message message){
        logger.info("Message {}", message.getPayload());
    }
}
