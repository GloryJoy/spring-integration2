package com.joyful.java.spring.integration.MessageDesignPattern.service;

import com.joyful.java.spring.integration.MessageDesignPattern.model.Swag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@MessageEndpoint
@Service
public class SwagServiceImpl implements SwagService{

    private static final Logger logger = LogManager.getLogger(SwagService.class);

    @ServiceActivator(inputChannel = "queueChannel", poller = @Poller(fixedDelay = "100"))
    @Override
    public void sendSwag(Message<Swag> message) throws RuntimeException {
        var payload = (Swag) message.getPayload();
        logger.info(String.format("SwagService::Sending Swag:{%s}", payload));

        if (payload.getType().equalsIgnoreCase("Hat")){
            throw new RuntimeException("We are out of hats.");
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

    }
}
