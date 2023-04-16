package com.joyful.java.spring.integration.MessageDesignPattern.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandlingException;
import org.springframework.stereotype.Service;

@Service
public class ErrorChannelService {
    private static final Logger logger = LogManager.getLogger(ErrorChannelService.class);

    @ServiceActivator(inputChannel = "customErrorChannel")
    void handleException(Message<MessageHandlingException> errorMessage){
        var ex = errorMessage.getPayload();

        logger.error("[Error] {}\n",
                ex.getCause().getMessage());
    }
}
